package study.daydayup.wolf.dts.transformer;

import lombok.Getter;
import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.dts.sink.Sink;
import study.daydayup.wolf.dts.source.offset.Offset;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.framework.dts.transeformer
 *
 * @author Wingle
 * @since 2020/2/16 6:54 下午
 **/
public class SinkTransformer implements Transformer {
    private final Offset offset;
    private final Statistics statistics;

    @Getter
    private Operator currentOperator;
    private final List<Operator> operatorList;

    public static SinkTransformer newTask(@NonNull Sink sink) {
        return new SinkTransformer(sink);
    }

    private SinkTransformer(@NonNull Sink sink) {
        offset = sink.getOffset();

        statistics = new Statistics();
        statistics.setAggregateKeys(sink.getGroupFields());
        sink.setStatistics(statistics);

        operatorList = new ArrayList<>(5);
    }

    public Operator addJob() {
        Operator operator = new Operator(statistics);

        operatorList.add(operator);
        currentOperator = operator;

        return currentOperator;
    }

    public void transform(Table table) {
        if (!CollectionUtil.notEmpty(table)) {
            return;
        }

        for (Row row : table) {
            transform(row);
        }
    }

    public void merge(@NonNull Row row) {
        if (operatorList.isEmpty()) {
            return;
        }

        for (Operator operator : operatorList) {
            operator.operate(row);
        }
    }

    public void format() {
        if (operatorList.isEmpty()) {
            return;
        }

        for (Operator operator : operatorList) {
            operator.format();
        }
    }

    private void transform(Row row) {
        if (null == row || operatorList.isEmpty()) {
            return;
        }

        if (isTransformed(row)) {
            return;
        }

        for (Operator operator : operatorList) {
            operator.operate(row);
        }
    }

    private boolean isTransformed(@NonNull Row row) {
        Long id = row.getLong(Table.DEFAULT_ID_COLUMN);
        if (id == null) {
            return false;
        }

        if (offset.getOffset() == null) {
            return false;
        }

        return id <= offset.getOffset();
    }

}
