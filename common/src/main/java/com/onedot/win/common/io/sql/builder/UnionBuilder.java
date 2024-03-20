package com.onedot.win.common.io.sql.builder;

import lombok.NonNull;
import com.onedot.win.common.util.lang.StringUtil;

/**
 * com.onedot.win.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 6:29 下午
 **/
public class UnionBuilder {
    public static SqlBuilder union(@NonNull SqlBuilder preBuilder, @NonNull String columns) {
        StringBuilder sql;
        sql = new StringBuilder(preBuilder.getSql());
        sql.append(SqlKeyword.UNION)
                .append(SqlKeyword.SELECT)
                .append(StringUtil.quote(columns, true));

        return new SqlBuilder(sql.toString(), preBuilder.isPrepared(), preBuilder.getPreparedDataList());
    }

    public static SqlBuilder unionAll(@NonNull SqlBuilder preBuilder, @NonNull String columns) {
        StringBuilder sql;
        sql = new StringBuilder(preBuilder.getSql());
        sql.append(SqlKeyword.UNION_ALL)
                .append(SqlKeyword.SELECT)
                .append(StringUtil.quote(columns, true));

        return new SqlBuilder(sql.toString(), preBuilder.isPrepared(), preBuilder.getPreparedDataList());
    }
}
