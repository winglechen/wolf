package study.daydayup.wolf.common.io.sql;

import lombok.NonNull;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.util.CollectionUtil;
import study.daydayup.wolf.common.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2020/1/19 1:27 下午
 **/
public class Sql {
    public static final String DEFAULT_KEY = "id";
    public static final String DEFAULT_COLUMNS = "*";
    public static final int DEFAULT_LIMIT = 10;

    private static final String SELECT = "SELECT ";
    private static final String INSERT = "INSERT INTO ";
    private static final String UPDATE = "UPDATE ";
    private static final String DELETE = "DELETE FROM ";

    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String VALUES = " VALUES ";
    private static final String AND = " AND ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String LIMIT = " LIMIT ";

    private static final String COMMA = ", ";
    private static final String BLANK = " ";

    private static final String GREATER_THAN = ">";
    private static final String LESS_THAN = "<";
    private static final String EQUAL = "=";
    private static final String QUESTION_MARK = "?";

    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";

    private StringBuilder sql;

    private boolean isFirstWhere = true;
    private boolean isFirstOrder = true;
    private boolean isFirstValue = true;

    public static Sql select(){
        return select(DEFAULT_COLUMNS);
    }

    public static Sql select(@NonNull String columns){
        String sql = StringUtil.join(SELECT, columns);
        return new Sql(sql);
    }

    public static Sql insert(@NonNull String table){
        String sql = StringUtil.join(INSERT, table);
        return new Sql(sql);
    }

    public static Sql update(@NonNull String table){
        String sql = StringUtil.join(UPDATE, table);
        return new Sql(sql);
    }

    public static Sql delete(@NonNull String table){
        String sql = StringUtil.join(DELETE, table);
        return new Sql(sql);
    }

    public static Sql scan(@NonNull String table, Long id, int limit) {
       return scan(table, DEFAULT_COLUMNS, id, limit, OrderEnum.ASC);
    }

    public static Sql scan(@NonNull String table, String columns, Long id) {
        return scan(table, columns, id, DEFAULT_LIMIT, OrderEnum.ASC);
    }

    public static Sql scan(@NonNull String table, String columns, Long id, int limit, OrderEnum order) {
        String clause = order == OrderEnum.DESC ? LESS_THAN : GREATER_THAN;
        String where = StringUtil.joinWith(StringUtil.BLANK, DEFAULT_KEY, clause, id);

        return select(columns == null ? DEFAULT_COLUMNS : columns)
                .from(table)
                .where(where)
                .orderBy(DEFAULT_KEY, order)
                .limit(limit)
                ;
    }

    public Sql() {
        sql = new StringBuilder();
    }

    public Sql(String query) {
        sql = new StringBuilder(query);
    }

    public Sql from(@NonNull String table) {
        sql.append(FROM).append(table);
        return this;
    }

    public Sql and(@NonNull String where) {
        return where(where);
    }

    public Sql where(@NonNull String where) {
        if (isFirstWhere) {
            sql.append(WHERE).append(where);
        } else {
            sql.append(AND).append(where);
        }

        isFirstWhere = false;
        return this;
    }

    public Sql orderBy(@NonNull String column, OrderEnum order) {
        if (isFirstOrder) {
            sql.append(ORDER_BY);
        } else {
            sql.append(COMMA);
        }
        sql.append(BLANK).append(column);
        sql.append(BLANK).append(order.getDesc());

        isFirstOrder = false;
        return this;
    }

    public Sql limit(int rows) {
        return limit(0, rows);
    }

    public Sql limit(int offset, int rows) {
        if (offset < 0) {
            throw new IllegalArgumentException("Sql offset can't less than 0");
        }

        sql.append(LIMIT);
        if (offset >  0) {
            sql.append(offset);
            sql.append(COMMA);
        }

        sql.append(rows);
        return this;
    }

    public Sql set(@NonNull Map<String, Object> data) {
        return set(data, false);
    }

    public Sql set(@NonNull Map<String, Object> data, boolean prepared) {
        if (data.isEmpty()) {
            return this;
        }

        boolean isFirst = true;
        for (Map.Entry<String, Object> entry: data.entrySet()) {
            if (!isFirst) {
                sql.append(",").append(BLANK);
            }
            isFirst = false;

            sql.append(entry.getKey()).append(BLANK).append(EQUAL).append(BLANK);

            if (prepared) {
                sql.append(QUESTION_MARK);
            } else {
                sql.append(formatValue(entry.getValue().toString()));
            }
        }

        return this;
    }

    public Sql values(@NonNull Map<String, Object> data) {
        return values(data, false);
    }

    public Sql values(@NonNull Map<String, Object> data, boolean prepared) {
        if (data.isEmpty()) {
            return this;
        }

        if (isFirstValue) {
            addInsertColumns(data);
        } else {
            sql.append(COMMA);
        }
        isFirstValue = false;

        if (prepared) {
            addPreparedInsertValues(data);
        } else {
            addInsertValues(data);
        }

        return this;
    }

    private void addInsertValues(@NonNull Map<String, Object> data) {
        String values = CollectionUtil.join("', '", data.values());
        sql.append(LEFT_BRACKET)
                .append("'") .append(values).append("'")
                .append(RIGHT_BRACKET);
    }

    private void addPreparedInsertValues(@NonNull Map<String, Object> data) {
        sql.append(LEFT_BRACKET);

        for (int i = 0, len=data.size(); i < len; i++) {
            if (0 != i) {
                sql.append(COMMA);
            }
            sql.append(QUESTION_MARK);
        }
        sql.append(RIGHT_BRACKET);
    }

    @Override
    public String toString() {
        return sql.toString();
    }

    private String formatValue(String value) {
        return StringUtil.join("'", value, "'");
    }

    private void addInsertColumns(@NonNull Map<String, Object> data) {
        String columns = String.join(", ", data.keySet());
        sql.append(LEFT_BRACKET)
                .append(columns)
                .append(RIGHT_BRACKET)
                .append(VALUES);
    }

    public static void main(String[] args) {
        System.out.println(Sql.scan("order", 10L, 10).toString());

        String select = Sql.select()
                .from("order")
                .where("orderNo = 'abc'")
                .toString();
        System.out.println("select: " + select);

        Map<String, Object> values = new HashMap<>();
        values.put("orderNo", "123456");
        values.put("amount", 123456);
        values.put("state", 1);

        String insert = Sql.insert("order")
                .values(values)
                .toString();
        System.out.println("insert: " + insert);

        Map<String, Object> data = new HashMap<>();
        data.put("amount", 123456);
        data.put("state", 10);
        String update = Sql.update("order")
                .set(data)
                .where("task_name = 'task'")
                .and("table_name = 'table'")
                .and("sharding_key = 'shard123'")
                .limit(1)
                .toString();
        System.out.println("update: " + update);

    }

}
