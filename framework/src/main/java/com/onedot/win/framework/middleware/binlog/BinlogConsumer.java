package com.onedot.win.framework.middleware.binlog;

import com.alibaba.dts.formats.avro.Field;
import com.alibaba.dts.formats.avro.Operation;
import com.alibaba.dts.formats.avro.Record;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.codehaus.jackson.map.ObjectMapper;
import com.onedot.win.common.io.db.Row;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.middleware.binlog.mysql.FieldConverter;
import com.onedot.win.framework.middleware.binlog.mysql.FieldEntryHolder;
import com.onedot.win.framework.middleware.binlog.mysql.FieldValue;
import com.onedot.win.framework.middleware.mq.core.consumer.AbstractConsumer;
import com.onedot.win.framework.middleware.mq.core.consumer.Consumer;
import com.onedot.win.framework.middleware.mq.core.consumer.ConsumeResult;
import com.onedot.win.framework.middleware.mq.core.message.Message;

import java.io.ByteArrayInputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * BinlogListener
 *
 * @author: YIK
 * @since: 2022/2/28 8:37 PM
 */
@Slf4j
public class BinlogConsumer extends AbstractConsumer implements Consumer {

    private final static ObjectMapper jackson = new ObjectMapper();
    private final BinlogProcessor binlogProcessor;
    //It's Thread-safe
    private final DatumReader<Record> datumReader = new SpecificDatumReader<>(Record.class);
    private final FieldConverter fieldConverter = FieldConverter.getConverter("mysql", null);
    private final Pattern tableNamePattern;
    private final String name;

    public BinlogConsumer(BinlogProcessor binlogProcessor, String tableNamePattern) {
        this.binlogProcessor = binlogProcessor;
        this.tableNamePattern = Pattern.compile(tableNamePattern);
        this.name = this.getClass().getSimpleName() + " - " + tableNamePattern;
    }

    @Override
    public ConsumeResult consume(List<Message> messageList) {
        if (CollectionUtil.isEmpty(messageList)) {
            log.warn("messageList is empty,return success to commit offset!");
            return ConsumeResult.SUCCESS();
        }

        if (binlogProcessor.consumeType().equals(BinlogConsumeTypeEnum.ORDERED)) {
            consumeOrdered(messageList);
        } else if (binlogProcessor.consumeType().equals(BinlogConsumeTypeEnum.GROUP_BATCHED)) {
            consumeGroupBatched(messageList);
        }

        Message lastMessage = messageList.get(messageList.size() - 1);
        return ConsumeResult.SUCCESS(lastMessage);
    }

    @Override
    public ConsumeResult consume(Message message) {
        return consume(Collections.singletonList(message));
    }

    private void consumeOrdered(List<Message> messageList) {
        messageList.forEach(message -> {
            AbstractMap.SimpleImmutableEntry<Operation, BinlogRow> entry = convertToRow(message);
            if (null == entry) {
                return;
            }

            if (entry.getKey().equals(Operation.INSERT)) {
                binlogProcessor.onInsert(entry.getValue());
            } else if (entry.getKey().equals(Operation.UPDATE)) {
                binlogProcessor.onUpdate(entry.getValue());
            }
        });
    }

    private void consumeGroupBatched(List<Message> messageList) {
        Map<Operation, List<BinlogRow>> rowsMap = new LinkedHashMap<>();
        rowsMap.put(Operation.INSERT, new ArrayList<>());
        rowsMap.put(Operation.UPDATE, new ArrayList<>());

        messageList.forEach(message -> {
            AbstractMap.SimpleImmutableEntry<Operation, BinlogRow> entry = convertToRow(message);
            if (null == entry) {
                return;
            }
            rowsMap.get(entry.getKey()).add(entry.getValue());
        });

        rowsMap.forEach((key, value) -> {
            if (key.equals(Operation.INSERT)) {
                if (!value.isEmpty()) {
                    binlogProcessor.onInsert(value);
                } else {
                    log.debug("[{}] insert rows is empty, messageList size: {}", name, messageList.size());
                }
            } else if (key.equals(Operation.UPDATE)) {
                if (!value.isEmpty()) {
                    binlogProcessor.onUpdate(value);
                } else {
                    log.debug("[{}] update rows is empty, messageList size: {}", name, messageList.size());
                }
            }
        });
    }

    private boolean filterBinlog(Record record) {
        if (null == record.getObjectName()) {
            return true;
        }

        String table = StringUtil.substringLastFrom(record.getObjectName(), ".", true);
        return !tableNamePattern.matcher(table).matches();
    }

    @SneakyThrows()
    /**
     * this function will change message.consumerContext.timestamp to record.sourceTimestamp
     */
    private AbstractMap.SimpleImmutableEntry<Operation, BinlogRow> convertToRow(Message message) {
        byte[] body = message.getBody();
        //It's not thread-safe,must new
        BinaryDecoder binaryDecoder = DecoderFactory.get().directBinaryDecoder(new ByteArrayInputStream(body), null);
        Record record = datumReader.read(new Record(), binaryDecoder);

        if (filterBinlog(record)) {
            return null;
        }

        if (record.getOperation() != Operation.INSERT
                && record.getOperation() != Operation.UPDATE) {
            return null;
        }

        try {
            //for alibaba cloud dts must use record.sourceTimestamp as checkpoint timestamp
            //this very important
            Long sourceTimestamp = record.getSourceTimestamp();
            message.getMessageContext().setTimestamp(sourceTimestamp);

            //for insert sql ,only need process afterImages
            List<Field> fields = (List<Field>) record.getFields();
            FieldEntryHolder after = new FieldEntryHolder((List<Object>) record.getAfterImages());
            if (null != fields) {
                Row row = new Row();
                Iterator<Field> fieldIterator = fields.iterator();
                while (fieldIterator.hasNext() && after.hasNext()) {
                    Field field = fieldIterator.next();
                    Object toPrintAfter = after.take();
                    try {
                        FieldValue fieldValue = fieldConverter.convert(field, toPrintAfter);
                        String name = field.getName();//CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getName());
                        row.put(name, fieldValue.getReal());
                    } catch (Exception e) {
                        log.error("fieldValue can not convert, field: {}, toPrintAfter: {} cause: {}", field, jackson.writeValueAsString(toPrintAfter), e.getMessage(), e);
                        throw e;
                    }
                }

                BinlogRow binlogRow = BinlogRow.builder()
                        .sourceTable(record.getObjectName())
                        .sourceTimestamp(record.getSourceTimestamp())
                        .data(row)
                        .build();
                return new AbstractMap.SimpleImmutableEntry<>(record.getOperation(), binlogRow);
            }

        } catch (Exception e) {
            log.error("can not covertToRow of message : {} cause: {}", jackson.writeValueAsString(message), e.getMessage(), e);
            return null;
        }

        return null;
    }
}
