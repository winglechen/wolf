package com.wolf.framework.middleware.rdb;

import com.wolf.common.io.enums.OrderEnum;
import com.wolf.common.io.sql.Sql;
import com.wolf.common.io.sql.SqlResult;
import com.wolf.common.io.sql.builder.SqlBuilder;
import com.wolf.common.io.sql.builder.SqlKeyword;
import lombok.NonNull;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

/**
 * com.wolf.framework.layer.dal
 *
 * @author Wingle
 * @since 2021/9/22 下午6:49
 **/
public class RDBTemplate {
    private final DbExecutor executor;

    public RDBTemplate(@NonNull DbExecutor sqlExecutor) {
        executor = sqlExecutor;
    }

    public void setDebug(boolean debug) {
        executor.setDebug(debug);
    }


    public SqlBuilder count(@NonNull String table) {
        return count(table, true);
    }

    public SqlBuilder count(@NonNull String table, boolean prepared) {
        return select(SqlKeyword.DEFAULT_COUNT, prepared).from(table);
    }

    public SqlBuilder exists(@NonNull String table) {
        return exists(table, true);
    }

    public SqlBuilder exists(@NonNull String table, boolean prepared) {
        return select(SqlKeyword.DEFAULT_KEY, prepared).from(table);
    }

    public SqlBuilder select() {
        return select(true);
    }

    public SqlBuilder select(boolean prepared) {
        return select(SqlKeyword.DEFAULT_COLUMNS, prepared);
    }

    public SqlBuilder select(@NonNull String columns) {
        return select(columns, true);
    }

    public SqlBuilder select(@NonNull String columns, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.select(columns, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public SqlBuilder insert(@NonNull String table) {
        return insert(table, true);
    }

    public SqlBuilder insert(@NonNull String table, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.insert(table, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public SqlBuilder insertIgnore(@NonNull String table) {
        return insertIgnore(table, true);
    }

    public SqlBuilder insertIgnore(@NonNull String table, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.insertIgnore(table, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public SqlBuilder update(@NonNull String table) {
        return update(table, true);
    }

    public SqlBuilder update(@NonNull String table, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.update(table, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public SqlBuilder delete(@NonNull String table) {
        return delete(table, true);
    }

    public SqlBuilder delete(@NonNull String table, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.delete(table, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public SqlBuilder scan(@NonNull String table, Long id, int limit) {
        return scan(table, SqlKeyword.DEFAULT_COLUMNS, id, limit, OrderEnum.ASC);
    }

    public SqlBuilder scan(@NonNull String table, String columns, Long id) {
        return scan(table, columns, id, SqlKeyword.DEFAULT_LIMIT, OrderEnum.ASC);
    }

    public SqlBuilder scan(@NonNull String table, String columns, Long id, int limit, OrderEnum order) {
        SqlBuilder sqlBuilder = Sql.scan(table, columns, id, limit, order);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public SqlResult execute(@NonNull String sql) {
        return executor.execute(sql);
    }

    public int[] batchUpdate(String sql, BatchPreparedStatementSetter pss) {
        return executor.batchUpdate(sql, pss);
    }

    public int[] batchUpdate(String sql, String table, Integer dbShard, Integer tableShard, BatchPreparedStatementSetter pss) {
        return executor.batchUpdate(sql, table, dbShard, tableShard, pss);
    }
}
