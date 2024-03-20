package com.onedot.win.framework.middleware.hbase.executor.hbase;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.framework.middleware.hbase.HBase;
import com.onedot.win.framework.middleware.hbase.OptionBuilder;
import com.onedot.win.framework.middleware.hbase.executor.AbstractExecutor;
import com.onedot.win.framework.middleware.hbase.executor.hbase.annotation.HBaseFiled;
import com.onedot.win.framework.middleware.hbase.option.*;
import com.onedot.win.framework.middleware.hbase.result.HBaseResult;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * HBaseExecutor
 *
 * @author rocky
 * @since 2023/7/27 14:54
 **/
@Slf4j
@Service
public class HBaseExecutor extends AbstractExecutor {

    @Resource
    private Map<String, Admin> admins;

    @PostConstruct
    public void init() {
        if (CollectionUtil.notEmpty(admins.entrySet())) {
            HBase.setExecutor(this);
        }
    }

    private Connection getConnection(String source) {
        return admins.get(source).getConnection();
    }

    public HBaseResult doGet(OptionBuilder optionBuilder, GetOptionCriteria getOption) {
        List<HBaseFieldParser> hBaseFieldParsers = parseClass(optionBuilder.getTable());
        try (Table table = getConnection(getOption.getSource()).getTable(TableName.valueOf(getOption.getTable()))) {
            Result result = table.get(toGet(getOption.getRowKey(), hBaseFieldParsers));
            Object data = toHBaseData(result, optionBuilder.getTable(), hBaseFieldParsers);
            return HBaseResult.success(data);
        } catch (Exception e) {
            log.error("HBase doGet failed", e);
            return HBaseResult.fail(e.getMessage());
        }
    }

    private Get toGet(String rowKey, List<HBaseFieldParser> hBaseFieldParsers) {
        Get get = new Get(Bytes.toBytes(rowKey));
        for (HBaseFieldParser hBaseFieldParser : hBaseFieldParsers) {
            get.addColumn(Bytes.toBytes(hBaseFieldParser.getFamily()), Bytes.toBytes(hBaseFieldParser.getQualifier()));
        }
        return get;
    }

    private Object toHBaseData(Result result, Class clazz, List<HBaseFieldParser> hBaseFieldParsers) throws Exception {
        Object instance = clazz.getConstructor().newInstance();
        for (HBaseFieldParser hBaseFieldParser : hBaseFieldParsers) {
            byte[] value = result.getValue(Bytes.toBytes(hBaseFieldParser.getFamily()), Bytes.toBytes(hBaseFieldParser.getQualifier()));
            Field field = hBaseFieldParser.getField();
            field.setAccessible(true);
            field.set(instance, toFieldValue(field, value));
        }
        return instance;
    }

    @Override
    public HBaseResult doGets(OptionBuilder optionBuilder, GetsOptionCriteria getsOption) {
        List<HBaseFieldParser> hBaseFieldParsers = parseClass(optionBuilder.getTable());
        List<Get> gets = getsOption.getRowKeys().stream()
            .map(rowKey -> toGet(rowKey, hBaseFieldParsers))
            .collect(Collectors.toList());
        try (Table table = getConnection(getsOption.getSource()).getTable(TableName.valueOf(getsOption.getTable()))) {
            Result[] results = table.get(gets);
            List<Object> list = new ArrayList<>();
            for (Result result : results) {
                Object o = toHBaseData(result, optionBuilder.getTable(), hBaseFieldParsers);
                list.add(o);
            }
            return HBaseResult.success(list);
        } catch (Exception e) {
            log.error("HBase doGets failed", e);
            return HBaseResult.fail(e.getMessage());
        }
    }


    public HBaseResult doPut(OptionBuilder optionBuilder, PutOptionCriteria putOption) {
        List<HBaseFieldParser> hBaseFieldParsers = parseClass(optionBuilder.getTable());
        try (Table table = getConnection(putOption.getSource()).getTable(TableName.valueOf(putOption.getTable()))) {
            Put put = toPut(putOption.getRowKey(), putOption.getData(), hBaseFieldParsers);
            table.put(put);
            return HBaseResult.success();
        } catch (Exception e) {
            log.error("HBase doPut failed", e);
            return HBaseResult.fail(e.getMessage());
        }
    }

    @Override
    public HBaseResult doPuts(OptionBuilder optionBuilder, PutsOptionCriteria putsOption) throws Exception {
        List<HBaseFieldParser> hBaseFieldParsers = parseClass(optionBuilder.getTable());
        ArrayList<Put> puts = new ArrayList<>();
        for (int i = 0; i < putsOption.getData().size(); i++) {
            String rowKey = putsOption.getRowKeys().get(i);
            puts.add(toPut(rowKey, putsOption.getData().get(i), hBaseFieldParsers));
        }
        try (Table table = getConnection(putsOption.getSource()).getTable(TableName.valueOf(putsOption.getTable()))) {
            table.put(puts);
            return HBaseResult.success();
        } catch (Exception e) {
            log.error("HBase doPuts failed", e);
            return HBaseResult.fail(e.getMessage());
        }
    }

    private Put toPut(String rowKey, Object data, List<HBaseFieldParser> hBaseFieldParsers) throws IllegalAccessException {
        Put put = new Put(Bytes.toBytes((rowKey)));
        for (HBaseFieldParser fieldParser : hBaseFieldParsers) {
            Field field = fieldParser.getField();
            field.setAccessible(true);
            put.addColumn(Bytes.toBytes(fieldParser.getFamily()), Bytes.toBytes(fieldParser.getQualifier()),
                toByte(field.get(data)));
        }
        return put;
    }


    public HBaseResult doScan(OptionBuilder optionBuilder, ScanOptionCriteria scanOption) {
        List<HBaseFieldParser> hBaseFieldParsers = parseClass(optionBuilder.getTable());
        Scan scan = new Scan();
        scan.setLimit(scanOption.getLimit());
        scan.withStartRow(Bytes.toBytes(scanOption.getStartRowKey()), scanOption.getIncludeStartRow());
        scan.withStopRow(Bytes.toBytes(scanOption.getStopRowKey()));
        try (Table table = getConnection(scanOption.getSource()).getTable(TableName.valueOf(scanOption.getTable()))) {
            ResultScanner scanner = table.getScanner(scan);
            Iterator<Result> iterator = scanner.iterator();
            List<Object> list = new ArrayList<>();
            while (iterator.hasNext()) {
                Object o = toHBaseData(iterator.next(), optionBuilder.getTable(), hBaseFieldParsers);
                list.add(o);
            }
            return HBaseResult.success(list);
        } catch (Exception e) {
            log.error("HBase doScan failed", e);
            return HBaseResult.fail(e.getMessage());
        }
    }

    private List<HBaseFieldParser> parseClass(Class clazz) {
        ArrayList<HBaseFieldParser> list = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            HBaseFiled[] hBaseFields = declaredField.getAnnotationsByType(HBaseFiled.class);
            if (hBaseFields.length > 0) {
                HBaseFieldParser hBaseFieldParser = new HBaseFieldParser(declaredField, hBaseFields[0].family(), hBaseFields[0].qualifier());
                list.add(hBaseFieldParser);
            }
        }
        return list;
    }

    private Object toFieldValue(Field field, byte[] value) {
        String typeName = field.getType().getTypeName();
        if (String.class.getName().equals(typeName)) {
            return Bytes.toString(value);
        } else if (Integer.class.getName().equals(typeName)) {
            return Bytes.toInt(value);
        } else if (Long.class.getName().equals(typeName)) {
            return Bytes.toLong(value);
        } else if (Float.class.getName().equals(typeName)) {
            return Bytes.toFloat(value);
        } else if (Double.class.getName().equals(typeName)) {
            return Bytes.toDouble(value);
        } else if (Boolean.class.getName().equals(typeName)) {
            return Bytes.toBoolean(value);
        } else if (Short.class.getName().equals(typeName)) {
            return Bytes.toShort(value);
        } else if (BigDecimal.class.getName().equals(typeName)) {
            return Bytes.toBigDecimal(value);
        }
        return null;
    }

    private byte[] toByte(Object value) {
        if (value instanceof String s) {
            return Bytes.toBytes(s);
        } else if (value instanceof Integer i) {
            return Bytes.toBytes(i);
        } else if (value instanceof Float f) {
            return Bytes.toBytes(f);
        } else if (value instanceof Double d) {
            return Bytes.toBytes(d);
        } else if (value instanceof Long l) {
            return Bytes.toBytes(l);
        } else if (value instanceof Boolean b) {
            return Bytes.toBytes(b);
        } else if (value instanceof Short sh) {
            return Bytes.toBytes(sh);
        } else if (value instanceof BigDecimal b) {
            return Bytes.toBytes(b);
        }
        return new byte[]{};
    }


}
