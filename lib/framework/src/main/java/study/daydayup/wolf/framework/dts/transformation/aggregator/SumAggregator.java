package study.daydayup.wolf.framework.dts.transformation.aggregator;

import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.util.lang.NumberUtil;

/**
 * study.daydayup.wolf.framework.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:27 下午
 **/
public class SumAggregator extends AbstractAggregator implements Aggregator {
    @Override
    public void aggregate(Row row) {
        Object rowValue = row.get(rowColumn);
        if (!(rowValue instanceof Number)) {
            return;
        }

        Object statValue = statistics.get(statisticsColumn);
        if (statValue == null) {
            statistics.set(statisticsColumn, rowValue);
            return;
        }

        statistics.set(statisticsColumn, NumberUtil.add((Number)rowValue, (Number)statValue));
    }

    @Override
    public void merge(Row row) {
    }

    @Override
    public void format() {
        Formatter.plus(statistics, statisticsColumn);
    }
}
