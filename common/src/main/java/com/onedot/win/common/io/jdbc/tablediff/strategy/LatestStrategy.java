package com.onedot.win.common.io.jdbc.tablediff.strategy;

import com.onedot.win.common.io.enums.OrderEnum;
import com.onedot.win.common.io.sql.Sql;

public class LatestStrategy implements TableStrategy {

    @Override
    public String getSql(String tableName) {

        return Sql.select("*", false)
                .from(tableName)
                .orderBy("id", OrderEnum.DESC)
                .limit(100)
                .getSql()
                ;
    }

}
