package study.daydayup.wolf.framework.dts.transformation.aggregator;

import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.framework.dts.transformation.Statistics;

/**
 * study.daydayup.wolf.framework.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/11 11:39 上午
 **/
public interface Aggregator {
    void init(Statistics statistics, String rowColumn, String statisticsColumn);
    void aggregate(Row row);
    void merge(Row row);
    void format();
}
