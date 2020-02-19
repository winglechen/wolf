package study.daydayup.wolf.framework.dts.transformation.aggregator;

import lombok.NonNull;
import study.daydayup.wolf.common.io.sql.SqlStatement;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.dts.transformation.Statistics;

import java.util.Collection;

/**
 * study.daydayup.wolf.framework.dts.transformation.aggregator
 *
 * @author Wingle
 * @since 2020/2/19 4:50 下午
 **/
public class Formatter {
    public static void plus(@NonNull Statistics statistics, @NonNull String column) {
        Object value = statistics.get(column);
        if (value == null) {
            return;
        }
        String sql = StringUtil.join(StringUtil.quote(column), " + ? ");
        statistics.set(column, SqlStatement.of(sql, value));
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
