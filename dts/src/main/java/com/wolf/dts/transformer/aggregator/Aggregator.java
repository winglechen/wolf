package com.wolf.dts.transformer.aggregator;

import com.wolf.common.io.db.Row;
import com.wolf.dts.transformer.Statistics;

/**
 * com.wolf.dts.transformation.aggregator
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
