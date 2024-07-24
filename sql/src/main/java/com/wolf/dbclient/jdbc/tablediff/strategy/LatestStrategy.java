package com.wolf.dbclient.jdbc.tablediff.strategy;

import com.wolf.dbclient.enums.OrderEnum;
import com.wolf.dbclient.sql.Sql;

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
