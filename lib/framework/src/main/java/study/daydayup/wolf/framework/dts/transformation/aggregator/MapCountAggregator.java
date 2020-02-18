package study.daydayup.wolf.framework.dts.transformation.aggregator;

import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.util.collection.MapUtil;

/**
 * study.daydayup.wolf.framework.dts.transformation.aggregator
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

        Integer value = (Integer)row.get(rowColumn);
        if (value == null) {
            return;
        }

        String sColumn = columnMap.get(value);
        if (sColumn == null) {
            return;
        }

        Integer count = (Integer) statistics.get(sColumn);
        if (count == null) {
            count = 0;
        }

        count++;
        statistics.set(sColumn, count);
    }


}
