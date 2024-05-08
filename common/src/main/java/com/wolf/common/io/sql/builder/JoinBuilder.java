package com.wolf.common.io.sql.builder;

import lombok.NonNull;
import com.wolf.common.util.lang.StringUtil;

/**
 * com.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 5:41 下午
 **/
public class JoinBuilder {
    public static String join(@NonNull String table, String alias, @NonNull String on) {
        StringBuilder sql = new StringBuilder();
        sql.append(SqlKeyword.BLANK).append(table);
        if (StringUtil.notBlank(alias)) {
            sql.append(SqlKeyword.BLANK).append(alias);
        }
        sql.append(SqlKeyword.ON).append(on);

        return sql.toString();
    }
}
