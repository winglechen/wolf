package study.daydayup.wolf.framework.dts.transformation.aggregator;

import lombok.NonNull;
import study.daydayup.wolf.framework.dts.transformation.Statistics;

import java.util.Map;

/**
 * study.daydayup.wolf.framework.dts.transformation.aggregator
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
