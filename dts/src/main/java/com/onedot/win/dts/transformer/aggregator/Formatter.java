package com.onedot.win.dts.transformer.aggregator;

import lombok.NonNull;
import com.onedot.win.common.io.sql.statement.SqlStatement;
import com.onedot.win.common.util.collection.CollectionUtil;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.dts.transformer.Statistics;

import java.util.Collection;

/**
 * com.onedot.win.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/19 4:50 下午
 **/
public class Formatter {
    public static void plus(@NonNull Statistics statistics, @NonNull String column) {
        Object value = statistics.getCurrentAggregateRowValue(column);
        if (value == null) {
            return;
        }
        String sql = StringUtil.join(StringUtil.quote(column), " + ? ");
        statistics.setCurrentAggregateRowValue(column, SqlStatement.of(sql, value));
    }

    public static void plus(@NonNull Statistics statistics, @NonNull Collection<String> columns) {
        if (CollectionUtil.isEmpty(columns)) {
            return;
        }

        for (String column : columns) {
            plus(statistics, column);
        }
    }
}
