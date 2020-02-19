package study.daydayup.wolf.common.io.sql;

/**
 * study.daydayup.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2020/2/6 11:13 下午
 **/
public class SqlStatement implements Statement {
    private String sql;
    private Object value;

    public static SqlStatement of(String sql) {
        return new SqlStatement(sql);
    }

    public static SqlStatement of(String sql, Object value) {
        return new SqlStatement(sql, value);
    }

    SqlStatement(String sql) {
        this(sql, null);
    }

    SqlStatement(String sql, Object value) {
        this.sql = sql;
        this.value = value;
    }

    @Override
    public String toString() {
        return sql;
    }

    @Override
    public String getSql() {
        return sql;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
