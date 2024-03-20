package com.onedot.win.common.io.sql.util;

import com.onedot.win.common.io.sql.statement.SqlStatement;
import com.onedot.win.common.util.collection.MapUtil;
import com.onedot.win.common.util.lang.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * com.onedot.win.common.io.sql
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
