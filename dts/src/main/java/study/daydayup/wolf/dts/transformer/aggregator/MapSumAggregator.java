package study.daydayup.wolf.dts.transformer.aggregator;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.NumberUtil;
import study.daydayup.wolf.dts.transformer.Statistics;

import java.util.Map;

/**
 * study.daydayup.wolf.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:27 下午
 **/
public class MapSumAggregator extends AbstractMapAggregator implements Aggregator {
    private String mapColumn;

    public void init(@NonNull Statistics statistics, @NonNull String mapColumn, @NonNull String rowColumn, @NonNull Map<Object, String> columnMap) {
        this.statistics = statistics;
        this.rowColumn = rowColumn;
        this.columnMap = columnMap;
        this.mapColumn = mapColumn;
    }

    @Override
    public void aggregate(Row row) {
        if (MapUtil.isEmpty(columnMap)) {
            return;
        }

        String sColumn = getStatisticColumn(row);
        if (sColumn == null) {
            return;
        }

        Object value = row.get(rowColumn);
        if (!(value instanceof Number)) {
            return;
        }

        Object sValue = statistics.getCurrentAggregateRowValue(sColumn);
        if (sValue == null) {
            statistics.setCurrentAggregateRowValue(sColumn, value);
            return;
        }

        statistics.setCurrentAggregateRowValue(sColumn, NumberUtil.add((Number) value, (Number) sValue));
    }

    @Override
    public void merge(Row row) {
    }

    @Override
    public void format() {
        Formatter.plus(statistics, columnMap.values());
    }

    private String getStatisticColumn(Row row) {
        Object mapValue = row.get(mapColumn);
        if (mapValue == null) {
            return null;
        }

        return columnMap.get(mapValue);
    }
}
