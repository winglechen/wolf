package study.daydayup.wolf.common.io.sql;

import lombok.NonNull;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.io.sql.builder.SqlBuilder;
import study.daydayup.wolf.common.io.sql.builder.SqlKeyword;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2020/1/19 1:27 下午
 **/
public class Sql {

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

    public static SqlBuilder select(@NonNull String columns) {
        return select(columns, true);
    }

    public static SqlBuilder select(@NonNull String columns, boolean prepared) {
        String sql = StringUtil.join(SqlKeyword.SELECT, StringUtil.quote(columns, true));
        return new SqlBuilder(sql, prepared);
    }

    public static SqlBuilder insert(@NonNull String table) {
        return insert(table, true);
    }

    public static SqlBuilder insert(@NonNull String table, boolean prepared) {
        String sql = StringUtil.join(SqlKeyword.INSERT, StringUtil.quote(table), StringUtil.BLANK);
        return new SqlBuilder(sql, prepared);
    }

    public static SqlBuilder insertIgnore(@NonNull String table) {
        return insertIgnore(table, true);
    }

    public static SqlBuilder insertIgnore(@NonNull String table, boolean prepared) {
        String sql = StringUtil.join(SqlKeyword.INSERT_IGNORE, StringUtil.quote(table), StringUtil.BLANK);
        return new SqlBuilder(sql, prepared);
    }


    public static SqlBuilder update(@NonNull String table) {
        return update(table, true);
    }

    public static SqlBuilder update(@NonNull String table, boolean prepared) {
        String sql = StringUtil.join(SqlKeyword.UPDATE, StringUtil.quote(table));
        return new SqlBuilder(sql, prepared);
    }

    public static SqlBuilder delete(@NonNull String table) {
        return delete(table, true);
    }

    public static SqlBuilder delete(@NonNull String table, boolean prepared) {
        String sql = StringUtil.join(SqlKeyword.DELETE, StringUtil.quote(table));
        return new SqlBuilder(sql, prepared);
    }

    public static SqlBuilder scan(@NonNull String table, Long id, int limit) {
        return scan(table, SqlKeyword.DEFAULT_COLUMNS, id, limit, OrderEnum.ASC);
    }

    public static SqlBuilder scan(@NonNull String table, String columns, Long id) {
        return scan(table, columns, id, SqlKeyword.DEFAULT_LIMIT, OrderEnum.ASC);
    }

    public static SqlBuilder scan(@NonNull String table, String columns, Long id, int limit, OrderEnum order) {
        String clause = order == OrderEnum.DESC ? SqlKeyword.LESS_THAN : SqlKeyword.GREATER_THAN;
        String where = StringUtil.joinWith(StringUtil.BLANK, SqlKeyword.DEFAULT_KEY, clause, id);

        return select(columns == null ? SqlKeyword.DEFAULT_COLUMNS : columns, false)
                .from(table)
                .where(where)
                .orderBy(SqlKeyword.DEFAULT_KEY, order)
                .limit(limit)
                ;
    }


//    public static void main(String[] args) {
//        System.out.println(Sql.scan("order", 10L, 10).toString());
//
//        String select = Sql.select()
//                .from("order")
//                .where("orderNo = 'abc'")
//                .toString();
//        System.out.println("select: " + select);
//
//        Map<String, Object> values = new HashMap<>();
//        values.put("orderNo", "123456");
//        values.put("amount", 123456);
//        values.put("state", 1);
//
//        String insert = Sql.insert("order", true)
//                .values(values)
//                .toString();
//        System.out.println("insert: " + insert);
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("amount", 123456);
//        data.put("state", 10);
//        data.put("version", SqlStatement.of("version + 1"));
//
//        SqlBuilder update = Sql.update("order")
//                .set(data)
//                .where("task_name = ?", "taskName")
//                .and("table_name = ?", "tableName")
//                .and("sharding_key = ?", "shardingKey")
//                .limit(1);
//        System.out.println("update: " + update);
//
//        String duplicateUpdate = Sql.insert("order")
//                .values(values)
//                .duplicateUpdate(data)
//                .toString();
//
//        System.out.println("duplicate: " + duplicateUpdate);
//
//
//        Map<String, Object> insertOrUpdate = new HashMap<>();
//        insertOrUpdate.put("org_id", 1);
//        insertOrUpdate.put("date", LocalDate.now());
//        insertOrUpdate.put("request_count", SqlStatement.of("request_count + ?", 10));
//        insertOrUpdate.put("order_amount", SqlStatement.of("order_amount + ?", 10000));
//
//        SqlBuilder sql = Sql.insert("order")
//                .values(insertOrUpdate)
//                .duplicateUpdate(insertOrUpdate);
//
//        System.out.println("insertOrUpdate: " +sql.toString());
//        System.out.println("insertOrUpdate: " +Arrays.asList(sql.getData()));
//
//    }

}
