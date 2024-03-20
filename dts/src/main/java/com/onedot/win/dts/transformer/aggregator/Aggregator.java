package com.onedot.win.dts.transformer.aggregator;

import com.onedot.win.common.io.db.Row;
import com.onedot.win.dts.transformer.Statistics;

/**
 * com.onedot.win.dts.transformation.aggregator
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
