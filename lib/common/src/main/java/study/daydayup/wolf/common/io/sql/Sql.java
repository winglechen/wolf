package study.daydayup.wolf.common.io.sql;

import lombok.NonNull;
import study.daydayup.wolf.common.io.enums.OrderEnum;
import study.daydayup.wolf.common.lang.string.Msg;

import java.util.Map;

/**
 * study.daydayup.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2020/1/19 1:27 下午
 **/
public class Sql {
    private static final String SELECT = "SELECT";
    private static final String INSERT = "INSERT INTO";
    private static final String UPDATE = "UPDATE";
    private static final String DELETE = "DELETE FROM";

    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String ORDER_BY = " ORDER BY ";
    private static final String LIMIT = " LIMIT ";

    private static final String COMMA = ", ";
    private static final String BLANK = " ";

    private static final String DEFAULT_KEY = "id";
    private static final String GREATER_THAN = " > ";
    private static final String LESS_THAN = " < ";

    private StringBuilder sql;

    private boolean isFirstWhere = true;
    private boolean isFirstOrder = true;

    public static Sql select(){
        return select("*");
    }

    public static Sql select(@NonNull String columns){
        String sql = Msg.join(SELECT, columns);
        return new Sql(sql);
    }

    public static Sql insert(@NonNull String table){
        String sql = Msg.join(INSERT, table);
        return new Sql(sql);
    }

    public static Sql update(@NonNull String table){
        String sql = Msg.join(UPDATE, table);
        return new Sql(sql);
    }

    public static Sql delete(@NonNull String table){
        String sql = Msg.join(DELETE, table);
        return new Sql(sql);
    }

    public static Sql iterator(@NonNull String table, Long from, int steps) {
       return iterator(table, from, steps, OrderEnum.ASC);
    }

    public static Sql iterator(@NonNull String table, Long from, int steps, OrderEnum order) {
        String clause = order == OrderEnum.DESC ? LESS_THAN : GREATER_THAN;
        String where = Msg.join(DEFAULT_KEY, clause, from);

        return select()
                .from(table)
                .where(where)
                .orderBy(DEFAULT_KEY, order)
                .limit(steps)
                ;
    }

    public Sql() {
        sql = new StringBuilder();
    }

    public Sql(String query) {
        sql = new StringBuilder(query);
    }

    public Sql from(@NonNull String table) {
        sql.append(FROM).append(table).append(" ");
        return this;
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

    public Sql set(Map<String, Object> data) {

        return this;
    }

    @Override
    public String toString() {
        return sql.toString();
    }

    public static void main(String[] args) {
        System.out.println(Sql.iterator("order", 10L, 10).toString());
    }
}
