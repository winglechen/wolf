package com.wolf.common.io.jdbc.tablediff.strategy;

import com.wolf.common.io.enums.OrderEnum;
import com.wolf.common.io.sql.Sql;

public class LastIdStrategy implements TableStrategy {
    private String tableName;

    @Override
    public String getSql(String tableName) {
        this.tableName = tableName;

        return Sql.select("id", false)
                .from(tableName)
                .orderBy("id", OrderEnum.DESC)
                .limit(1)
                .getSql()
                ;
    }

}
