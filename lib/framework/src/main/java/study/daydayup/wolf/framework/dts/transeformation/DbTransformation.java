package study.daydayup.wolf.framework.dts.transeformation;

import lombok.Getter;
import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Operator;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.framework.dts.config.SinkConfig;
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

    @Getter
    private Operator currentOperator;
    private List<Operator> operatorList;

    public static DbTransformation newTask(@NonNull Sink sink) {
        return new DbTransformation(sink);
    }

    private DbTransformation(Sink sink) {
        this.sink = sink;
        this.operatorList = new ArrayList<>(5);
    }

    public void addJob() {

    }


    public Statistics transform(Table table) {
        return null;
    }

}
