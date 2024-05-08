package com.wolf.dts.transformer.aggregator;

import com.wolf.common.io.db.Row;
import com.wolf.common.util.collection.MapUtil;

/**
 * com.wolf.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:27 下午
 **/
public class MapCountAggregator extends AbstractMapAggregator implements Aggregator {

    @Override
    public void aggregate(Row row) {
        if (MapUtil.isEmpty(columnMap)) {
            return;
        }

        Integer value = (Integer) row.get(rowColumn);
        if (value == null) {
            return;
        }

        String sColumn = columnMap.get(value);
        if (sColumn == null) {
            return;
        }

        Integer count = (Integer) statistics.getCurrentAggregateRowValue(sColumn);
        if (count == null) {
            count = 0;
        }

        count++;
        statistics.setCurrentAggregateRowValue(sColumn, count);
    }

    @Override
    public void format() {
        Formatter.plus(statistics, columnMap.values());
    }


}
