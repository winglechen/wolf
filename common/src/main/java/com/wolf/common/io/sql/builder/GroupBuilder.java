package com.wolf.common.io.sql.builder;

import lombok.NonNull;
import com.wolf.common.util.lang.StringUtil;

import java.util.List;

public class GroupBuilder {
    public static String groupBy(@NonNull String column, boolean isFirstGroup) {
        StringBuilder sql = new StringBuilder();
        if (isFirstGroup) {
            sql.append(SqlKeyword.GROUP_BY);
        } else {
            sql.append(SqlKeyword.COMMA);
        }
        sql.append(column);

        return sql.toString();
    }

    public static String groupBy(@NonNull List<String> columns, boolean isFirstGroup) {
        StringBuilder sql = new StringBuilder();
        if (isFirstGroup) {
            sql.append(SqlKeyword.GROUP_BY);
        } else {
            sql.append(SqlKeyword.COMMA);
        }
        sql.append(StringUtil.joinWith(SqlKeyword.COMMA, columns));

        return sql.toString();
    }
}
