package com.wolf.pangu.dbclient;

import com.wolf.common.io.db.Table;
import com.wolf.common.io.enums.OrderEnum;
import com.wolf.common.util.collection.CollectionUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RDBChecker {
    public static Map<String, Boolean> check(String... tables) {
        return check(Arrays.asList(tables));
    }

    public static Map<String, Boolean> check(List<String> tables) {
        Map<String, Boolean> result = new HashMap<>();

        for (String table : tables) {
            Table rows = RDB.select("id")
                    .from(table)
                    .orderBy("id", OrderEnum.DESC)
                    .limit(1)
                    .execute()
                    .getTable();

            if (CollectionUtil.notEmpty(rows)) {
                result.put(table, true);
            } else {
                result.put(table, false);
            }
        }

        return result;
    }
}
