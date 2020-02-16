package study.daydayup.wolf.framework.dts.transeformation;

import lombok.Getter;
import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Operator;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.framework.dts.sink.Sink;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.framework.dts.transeformer
 *
 * @author Wingle
 * @since 2020/2/16 6:54 下午
 **/
public class DbTransformation implements Transformation {
    private Sink sink;
    private Statistics statistics;

    @Getter
    private Operator currentOperator;
    private List<Operator> operatorList;

    public static Operator newTask(@NonNull Sink sink) {
        return new DbTransformation(sink).getCurrentOperator();
    }

    private DbTransformation(Sink sink) {
        this.sink = sink;

        statistics = new Statistics();
        statistics.setKeyColumns(sink.getKeyColumns());

        operatorList = new ArrayList<>(5);
        currentOperator = new Operator(statistics);
        operatorList.add(currentOperator);
    }

    public Operator addJob() {
        Operator operator = new Operator(statistics);

        operatorList.add(operator);
        currentOperator = operator;

        return currentOperator;
    }


    public Statistics transform(Table table) {
        return statistics;
    }

}
