package com.wolf.dts.transformer.aggregator;

import lombok.NonNull;
import com.wolf.dts.transformer.Statistics;

import java.util.Map;

/**
 * com.wolf.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:27 下午
 **/
public abstract class AbstractMapAggregator extends AbstractAggregator implements Aggregator {
    protected Map<Object, String> columnMap;

    public void init(@NonNull Statistics statistics, @NonNull String rowColumn, @NonNull Map<Object, String> columnMap) {
        this.statistics = statistics;
        this.rowColumn = rowColumn;
        this.columnMap = columnMap;
    }


}
