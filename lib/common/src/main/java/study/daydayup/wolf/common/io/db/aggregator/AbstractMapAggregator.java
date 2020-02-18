package study.daydayup.wolf.common.io.db.aggregator;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Statistics;

import java.util.Map;

/**
 * study.daydayup.wolf.common.io.db.aggregator
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
