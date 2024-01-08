package study.daydayup.wolf.dts.transformer.aggregator;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.dts.transformer.Statistics;

/**
 * study.daydayup.wolf.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:28 下午
 **/
public abstract class AbstractAggregator implements Aggregator {
    protected String rowColumn;
    protected String statisticsColumn;
    protected Row row;
    protected Statistics statistics;

    @Override
    public void init(@NonNull Statistics statistics, @NonNull String rowColumn, @NonNull String statisticsColumn) {
        this.statistics = statistics;
        this.rowColumn = rowColumn;
        this.statisticsColumn = statisticsColumn;
    }

    @Override
    public void merge(Row row) {
    }
}
