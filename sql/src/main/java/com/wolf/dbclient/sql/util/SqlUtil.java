package com.wolf.dbclient.sql.util;

import com.wolf.dbclient.sql.statement.SqlStatement;
import com.wolf.common.util.collection.MapUtil;
import com.wolf.common.util.lang.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * com.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2020/5/17 2:22 上午
 **/
public class SqlUtil {
    public static Map<String, Object> toAdd(Map<String, Object> data) {
        if (MapUtil.isEmpty(data)) {
            return data;
        }

        Map<String, Object> result = new HashMap<>();

        SqlStatement statement;
        String sql;
        for (String key: data.keySet()) {
            sql = StringUtil.join(key, " + ? ");
            statement = SqlStatement.of(sql, data.get(key));

            result.put(key, statement);
        }

        return result;
    }

}
