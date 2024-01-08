package study.daydayup.wolf.framework.middleware.rdb;

import lombok.NonNull;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.io.sql.Sql;
import study.daydayup.wolf.common.io.sql.SqlExecutor;
import study.daydayup.wolf.common.io.sql.SqlResult;
import study.daydayup.wolf.common.io.sql.builder.SqlBuilder;
import study.daydayup.wolf.common.io.sql.builder.SqlKeyword;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * study.daydayup.wolf.framework.layer.dal
 *
 * @author Wingle
 * @since 2021/9/22 下午6:49
 **/
public class RDB {
    private static SqlExecutor executor;

    public static void setDebug(boolean debug) {
        if (executor == null) {
            return;
        }

        executor.setDebug(debug);
    }

    public static void setExecutor(@NonNull SqlExecutor sqlExecutor) {
        executor = sqlExecutor;
    }

    public static SqlBuilder count(@NonNull String table) {
        return count(table, true);
    }

    public static SqlBuilder count(@NonNull String table, boolean prepared) {
        return select(SqlKeyword.DEFAULT_COUNT, prepared).from(table);
    }

    public static SqlBuilder exists(@NonNull String table) {
        return exists(table, true);
    }

    public static SqlBuilder exists(@NonNull String table, boolean prepared) {
        return select(SqlKeyword.DEFAULT_KEY, prepared).from(table);
    }

    public static SqlBuilder select() {
        return select(true);
    }

    public static SqlBuilder select(boolean prepared) {
        return select(SqlKeyword.DEFAULT_COLUMNS, prepared);
    }

    public static SqlBuilder select(String ... columns){
       return select(Arrays.stream(columns).collect(Collectors.joining(",")));
    }

    public static SqlBuilder select(@NonNull String columns) {
        return select(columns, true);
    }

    public static SqlBuilder select(@NonNull String columns, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.select(columns, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public static SqlBuilder insert(@NonNull String table) {
        return insert(table, true);
    }

    public static SqlBuilder insert(@NonNull String table, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.insert(table, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public static SqlBuilder insertIgnore(@NonNull String table) {
        return insertIgnore(table, true);
    }

    public static SqlBuilder insertIgnore(@NonNull String table, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.insertIgnore(table, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public static SqlBuilder update(@NonNull String table) {
        return update(table, true);
    }

    public static SqlBuilder update(@NonNull String table, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.update(table, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public static SqlBuilder delete(@NonNull String table) {
        return delete(table, true);
    }

    public static SqlBuilder delete(@NonNull String table, boolean prepared) {
        SqlBuilder sqlBuilder = Sql.delete(table, prepared);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public static SqlBuilder scan(@NonNull String table, Long id, int limit) {
        return scan(table, SqlKeyword.DEFAULT_COLUMNS, id, limit, OrderEnum.ASC);
    }

    public static SqlBuilder scan(@NonNull String table, String columns, Long id) {
        return scan(table, columns, id, SqlKeyword.DEFAULT_LIMIT, OrderEnum.ASC);
    }

    public static SqlBuilder scan(@NonNull String table, String columns, Long id, int limit, OrderEnum order) {
        SqlBuilder sqlBuilder = Sql.scan(table, columns, id, limit, order);
        sqlBuilder.setExecutor(executor);
        return sqlBuilder;
    }

    public static SqlResult execute(@NonNull String sql) {
        return executor.execute(sql);
    }
}
