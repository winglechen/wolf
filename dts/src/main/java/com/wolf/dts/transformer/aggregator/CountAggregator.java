package com.wolf.dts.transformer.aggregator;

import com.wolf.common.io.db.Row;

/**
 * com.wolf.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:27 下午
 **/
public class CountAggregator extends AbstractAggregator implements Aggregator {

    @Override
    public void aggregate(Row row) {
        Integer count = (Integer) statistics.getCurrentAggregateRowValue(statisticsColumn);
        if (count == null) {
            count = 0;
        }

        count++;
        statistics.setCurrentAggregateRowValue(statisticsColumn, count);
    }

    @Override
    public void merge(Row row) {
        Integer rowCount = (Integer) row.get(statisticsColumn);
        if (rowCount == null) {
            rowCount = 0;
        }

        Integer statCount = (Integer) statistics.getCurrentAggregateRowValue(statisticsColumn);
        if (statCount == null) {
            statCount = 0;
        }

        Integer count = rowCount + statCount;
        statistics.setCurrentAggregateRowValue(statisticsColumn, count);
    }

    @Override
    public void format() {
        Formatter.plus(statistics, statisticsColumn);
    }

}
