package com.wolf.common.io.sql.builder;

/**
 * com.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 6:21 下午
 **/
public class LimitBuilder {
    public static String limit(int offset, int limit) {
        if (offset < 0) {
            throw new IllegalArgumentException("Sql offset can't less than 0");
        }

        StringBuilder sql = new StringBuilder();
        sql.append(SqlKeyword.LIMIT);
        if (offset > 0) {
            sql.append(offset);
            sql.append(SqlKeyword.COMMA);
        }

        sql.append(limit);

        return sql.toString();
    }
}
