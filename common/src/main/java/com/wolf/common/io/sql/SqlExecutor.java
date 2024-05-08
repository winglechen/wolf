package com.wolf.common.io.sql;

import com.wolf.common.io.sql.builder.SqlBuilder;

/**
 * com.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2021/9/22 下午9:30
 **/
public interface SqlExecutor {
    void setDebug(boolean debug);

    SqlResult execute(SqlBuilder sqlBuilder);

    SqlResult execute(SqlBuilder sqlBuilder, boolean debugInCurrentSession);

    SqlResult execute(String sql);
}
