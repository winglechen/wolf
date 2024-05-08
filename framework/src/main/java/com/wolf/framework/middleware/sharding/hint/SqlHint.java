package com.wolf.framework.middleware.sharding.hint;

import org.apache.shardingsphere.api.hint.HintManager;

/**
 * com.wolf.framework.middleware.sharding.hint
 *
 * @author Wingle
 * @since 2021/12/29 上午1:22
 **/
public class SqlHint implements AutoCloseable {
    private final HintManager hintManager;

    public static SqlHint getInstance() {
        return new SqlHint();
    }

    public static SqlHint hint(String table, Comparable<?> dbShard, Comparable<?> tableShard) {
        SqlHint hint = new SqlHint();
        return hint.add(table, dbShard, tableShard);
    }

    public SqlHint() {
        hintManager = HintManager.getInstance();
    }

    public SqlHint useMaster() {
        hintManager.setMasterRouteOnly();
        return this;
    }

    public SqlHint add(String table, Comparable<?> dbShard, Comparable<?> tableShard) {
        hintManager.addDatabaseShardingValue(table, dbShard);
        hintManager.addTableShardingValue(table, tableShard);

        return this;
    }

    @Override
    public void close() {
        hintManager.close();
    }


}
