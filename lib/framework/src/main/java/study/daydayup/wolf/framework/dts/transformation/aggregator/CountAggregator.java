package study.daydayup.wolf.framework.dts.transformation.aggregator;

import study.daydayup.wolf.common.io.db.Row;

/**
 * study.daydayup.wolf.framework.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:27 下午
 **/
public class CountAggregator extends AbstractAggregator implements Aggregator {

    @Override
    public void aggregate(Row row) {
        Integer count = (Integer) statistics.get(statisticsColumn);
        if (count == null) {
            count = 0;
        }

        count++;
        statistics.set(statisticsColumn, count);
    }

    @Override
    public void merge(Row row) {
        Integer rowCount  = (Integer) row.get(statisticsColumn);
        if (rowCount == null) {
            rowCount = 0;
        }

        Integer statCount = (Integer) statistics.get(statisticsColumn);
        if (statCount == null) {
            statCount = 0;
        }

        Integer count = rowCount + statCount;
        statistics.set(statisticsColumn, count);
    }

    @Override
    public void format() {
        Formatter.plus(statistics, statisticsColumn);
    }

}
