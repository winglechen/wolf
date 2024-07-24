package com.wolf.dbclient.sql.builder;

import lombok.NonNull;
import com.wolf.common.util.lang.StringUtil;

/**
 * com.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/22 6:46 下午
 **/
public class FromBuilder {
    public static String from(@NonNull String table, String alias) {
        StringBuilder sql = new StringBuilder();
        sql.append(SqlKeyword.FROM).append(StringUtil.quote(table));

        if (StringUtil.notBlank(alias)) {
            sql.append(SqlKeyword.BLANK).append(alias);
        }
        return sql.toString();
    }
}
