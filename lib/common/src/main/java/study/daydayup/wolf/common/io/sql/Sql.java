package study.daydayup.wolf.common.io.sql;

import lombok.NonNull;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.util.time.DateUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * study.daydayup.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2020/1/19 1:27 下午
 **/
public class Sql {
    public static final String DEFAULT_KEY = "id";
    public static final String DEFAULT_COLUMNS = "*";
    public static final String DEFAULT_COUNT = " count(*) as count ";
    public static final int DEFAULT_LIMIT = 10;

    private static final String SELECT = "SELECT ";
    private static final String INSERT = "INSERT INTO ";
    private static final String UPDATE = "UPDATE ";
    private static final String DELETE = "DELETE FROM ";

    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String SET = " SET ";
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
    private boolean prepared;
    private List<Object> data;

    private boolean isFirstWhere = true;
    private boolean isFirstOrder = true;
    private boolean isFirstValue = true;

    public static Sql count() {
        return count(true);
    }

    public static Sql count(boolean prepared) {
        return select(DEFAULT_COUNT, prepared);
    }

    public static Sql exists() {
        return exists(true);
    }

    public static Sql exists(boolean prepared) {
        return select(DEFAULT_KEY, prepared);
    }

    public static Sql select(){
        return select(true);
    }

    public static Sql select(boolean prepared) {
        return select(DEFAULT_COLUMNS, prepared);
    }

    public static Sql select(@NonNull String columns){
        return select(columns, true);
    }

    public static Sql select(@NonNull String columns, boolean prepared){
        String sql = StringUtil.join(SELECT, StringUtil.quote(columns, true));
        return new Sql(sql, prepared);
    }

    public static Sql insert(@NonNull String table){
        return insert(table, true);
    }

    public static Sql insert(@NonNull String table, boolean prepared){
        String sql = StringUtil.join(INSERT, StringUtil.quote(table), StringUtil.BLANK);
        return new Sql(sql, prepared);
    }

    public static Sql update(@NonNull String table){
        return update(table, true);
    }

    public static Sql update(@NonNull String table, boolean prepared){
        String sql = StringUtil.join(UPDATE, StringUtil.quote(table));
        return new Sql(sql, prepared);
    }

    public static Sql delete(@NonNull String table){
        return delete(table, true);
    }

    public static Sql delete(@NonNull String table, boolean prepared){
        String sql = StringUtil.join(DELETE, StringUtil.quote(table));
        return new Sql(sql, prepared);
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
        this(StringUtil.EMPTY, true);
    }

    public Sql(boolean prepared) {
        this(StringUtil.EMPTY, prepared);
    }

    public Sql(String query) {
        this(query, true);
    }

    public Sql(String query, boolean prepared) {
        sql = new StringBuilder(query);
        this.prepared = prepared;

        this.data = new LinkedList<>();
    }

    public Object[] getData() {
        return data.toArray();
    }

    public String getSql() {
        return sql.toString();
    }

    public Sql from(@NonNull String table) {
        sql.append(FROM).append(StringUtil.quote(table));
        return this;
    }

    public Sql and(@NonNull String where, Object... ps) {
        return where(where, ps);
    }

    public Sql and(@NonNull String where) {
        return where(where);
    }

    /**
     * TODO: Statement support
     */
    public Sql where(@NonNull Map<String, Object> ps) {
        if (0 == ps.size()) {
            return this;
        }
        addWherePrefix();
        for (Map.Entry<String, Object> entry: ps.entrySet()) {
            String column = StringUtil.quote(entry.getKey());
            sql.append(column).append(BLANK).append(EQUAL).append(BLANK);

            Object value = entry.getValue();
            if (prepared) {
                sql.append(QUESTION_MARK).append(BLANK);
                data.add(value);
            } else {
                sql.append(formatValue(value));
            }
        }

        return this;
    }

    public Sql where(@NonNull String where, Object... ps) {
        addWherePrefix();

        sql.append(where);
        if (prepared) {
            data.addAll(Arrays.asList(ps));
        }
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
        if (data.isEmpty()) {
            return this;
        }

        boolean isFirst = true;
        for (Map.Entry<String, Object> entry: data.entrySet()) {
            if (!isFirst) {
                sql.append(",").append(BLANK);
            }else {
                sql.append(SET);
            }
            isFirst = false;

            sql.append(StringUtil.quote(entry.getKey()))
                    .append(BLANK).append(EQUAL).append(BLANK);

            Object value = entry.getValue();
            if (value instanceof Statement) {
                sql.append(value.toString());
            } else if (prepared) {
                this.data.add(value);
                sql.append(QUESTION_MARK);
            } else {
                sql.append(formatValue(value));
            }
        }

        return this;
    }

    public Sql values(@NonNull Map<String, Object> data) {
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

    @Override
    public String toString() {
        return sql.toString();
    }

    private void addWherePrefix() {
        if (isFirstWhere) {
            sql.append(WHERE);
        } else {
            sql.append(AND);
        }
        isFirstWhere = false;
    }

    private Object formatValue(Object value) {
        if (value instanceof Statement) {
            return value.toString();
        }

        if (value instanceof Number) {
            return value;
        }

        if (value instanceof Date) {
            return StringUtil.join("'", DateUtil.asString((Date) value), "'");
        }

        if (value instanceof LocalDate) {
            return StringUtil.join("'", DateUtil.asString((LocalDate) value), "'");
        }

        if (value instanceof LocalDateTime) {
            return StringUtil.join("'", DateUtil.asString((LocalDateTime) value), "'");
        }

        return StringUtil.join("'", value, "'");
    }

    private void addInsertColumns(@NonNull Map<String, Object> data) {
        String columns = String.join("`, `", data.keySet());

        sql.append(LEFT_BRACKET)
                .append("`")
                .append(columns)
                .append("`")
                .append(RIGHT_BRACKET)
                .append(VALUES);
    }

    private void addInsertValues(@NonNull Map<String, Object> data) {
        sql.append(LEFT_BRACKET);

        Object[] values = data.values().toArray();
        for (int i = 0, len=values.length; i < len; i++) {
            if (0 != i) {
                sql.append(COMMA);
            }
            sql.append(formatValue(values[i]));

            if (prepared) {
                this.data.add(values[i]);
            }
        }

        sql.append(RIGHT_BRACKET);
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
        data.put("version", SqlStatement.of("version + 1"));

        Sql update = Sql.update("order")
                .set(data)
                .where("task_name = ?", "taskName")
                .and("table_name = ?", "tableName")
                .and("sharding_key = ?", "shardingKey")
                .limit(1);
        System.out.println("update: " + update);

        for (Object o : update.getData()) {
            System.out.println("prepared: " + o);
        }

    }

}
