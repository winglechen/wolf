package study.daydayup.wolf.framework.dts.transformation.aggregator;

import lombok.NonNull;
import study.daydayup.wolf.common.io.sql.SqlStatement;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.dts.transformation.Statistics;

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
        String sql = StringUtil.join(column, " + ? ");
        statistics.set(column, SqlStatement.of(sql, value));
    }
}
