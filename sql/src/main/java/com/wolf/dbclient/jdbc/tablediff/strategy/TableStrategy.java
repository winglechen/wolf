package com.wolf.dbclient.jdbc.tablediff.strategy;

public interface TableStrategy {
    String getSql(String tableName);
}
