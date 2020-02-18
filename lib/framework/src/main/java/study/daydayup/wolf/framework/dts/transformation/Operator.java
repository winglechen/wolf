package study.daydayup.wolf.framework.dts.transformation;

import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.framework.dts.transformation.aggregator.AggregatorGateway;
import study.daydayup.wolf.framework.dts.transformation.mapper.MapperGateway;
import study.daydayup.wolf.framework.dts.transformation.matcher.MatcherGateway;
import study.daydayup.wolf.common.util.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/11 11:22 上午
 **/
public class Operator {
    private MapperGateway mapper;
    private MatcherGateway matcher;
    private AggregatorGateway aggregator;
    private Statistics statistics;

    private static Statistics lastStatistics;
    private static List<Operator> operatorList;

    public static Operator addJob() {
        return newTask(null);
    }

    public static Operator newTask(Statistics statistics) {
        if (statistics != null) {
            operatorList = new ArrayList<>(5);
            lastStatistics = statistics;
        }

        Operator operator = new Operator(lastStatistics);

        operatorList.add(operator);

        return operator;
    }

    public static void execute(Table table) {
        if (!CollectionUtil.hasValue(table)) {
            return;
        }

        for (Row row : table) {
            execute(row);
        }
    }

    public static void execute(Row row) {
        if (operatorList.isEmpty()) {
            return;
        }

        for (Operator operator : operatorList) {
            operator.operate(row);
        }
    }

    public Operator() {}

    public Operator(Statistics statistics) {
        this.statistics = statistics;
    }

    public void merge(Row row) {
        if (null != aggregator) {
            aggregator.merge(row);
        }

    }

    public void operate(Row row) {
        if (mapper != null) {
            mapper.map(row);
        }

        if (null != matcher) {
            if (!matcher.match(row)) {
                return;
            }
        }

        if (null != aggregator) {
            aggregator.aggregate(row);
        }
    }

    public MatcherGateway match() {
        if (null == matcher) {
            matcher = new MatcherGateway();
        }

        return matcher;
    }

    public AggregatorGateway aggregate() {
        if (aggregator == null) {
            aggregator = new AggregatorGateway(statistics);
        }

        return aggregator;
    }

    public MapperGateway map() {
        if (mapper == null) {
            mapper = new MapperGateway();
        }

        return mapper;
    }
}