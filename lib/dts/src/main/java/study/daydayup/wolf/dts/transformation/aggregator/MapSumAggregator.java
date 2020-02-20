package study.daydayup.wolf.dts.transformation.aggregator;

import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.NumberUtil;

/**
 * study.daydayup.wolf.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:27 下午
 **/
public class MapSumAggregator extends AbstractMapAggregator implements Aggregator {

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

        Object sValue = statistics.get(sColumn);
        if (sValue == null) {
            statistics.set(sColumn, value);
            return;
        }

        statistics.set(sColumn, NumberUtil.add(value, (Number)sValue));
    }

    @Override
    public void merge(Row row) { }

    @Override
    public void format() {
        Formatter.plus(statistics, columnMap.values());
    }


}
