package com.wolf.dts.transformer.aggregator;

import com.wolf.common.io.db.Row;
import com.wolf.common.util.lang.NumberUtil;

/**
 * com.wolf.dts.transformation.aggregator
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

        Object statValue = statistics.getCurrentAggregateRowValue(statisticsColumn);
        if (statValue == null) {
            statistics.setCurrentAggregateRowValue(statisticsColumn, rowValue);
            return;
        }

        statistics.setCurrentAggregateRowValue(statisticsColumn, NumberUtil.add((Number) rowValue, (Number) statValue));
    }

    @Override
    public void merge(Row row) {
    }

    @Override
    public void format() {
        Formatter.plus(statistics, statisticsColumn);
    }
}
