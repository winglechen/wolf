package study.daydayup.wolf.dts.transformer.aggregator;

import lombok.NonNull;
import study.daydayup.wolf.common.io.sql.statement.SqlStatement;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.dts.transformer.Statistics;

import java.util.Collection;

/**
 * study.daydayup.wolf.dts.transformation.aggregator
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
