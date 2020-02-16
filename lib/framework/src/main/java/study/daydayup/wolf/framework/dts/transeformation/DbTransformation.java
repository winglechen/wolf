package study.daydayup.wolf.framework.dts.transeformation;

import lombok.Getter;
import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Operator;
import study.daydayup.wolf.common.io.db.Statistics;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.framework.dts.config.SinkConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.framework.dts.transeformer
 *
 * @author Wingle
 * @since 2020/2/16 6:54 下午
 **/
public class DbTransformation implements Transformation {
    private SinkConfig config;

    @Getter
    private Operator currentOperator;
    private List<Operator> operatorList;

    public static DbTransformation newTask(@NonNull SinkConfig config) {
        return new DbTransformation(config);
    }

    private DbTransformation(SinkConfig config) {
        this.config = config;
        this.operatorList = new ArrayList<>(5);
    }

    public void addJob() {

    }


    public Statistics transform(Table table) {
        return null;
    }

}
