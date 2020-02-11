package study.daydayup.wolf.common.io.db.aggregator;

import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Statistics;

/**
 * study.daydayup.wolf.common.io.db.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 5:28 下午
 **/
public class AbstractAggregator {
    protected String rowColumn;
    protected String statisticsColumn;
    protected Row row;
    protected Statistics statistics;

    public void init(Statistics statistics, String rowColumn, String statisticsColumn) {
        this.statistics = statistics;
        this.rowColumn = rowColumn;
        this.statisticsColumn = statisticsColumn;
    }
}
