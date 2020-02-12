package study.daydayup.wolf.common.io.db.aggregator;

import study.daydayup.wolf.common.io.db.Row;

/**
 * study.daydayup.wolf.common.io.db.aggregator
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
}
