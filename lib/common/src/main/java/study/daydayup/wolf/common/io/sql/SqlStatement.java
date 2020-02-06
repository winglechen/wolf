package study.daydayup.wolf.common.io.sql;

/**
 * study.daydayup.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2020/2/6 11:13 下午
 **/
public class SqlStatement implements Statement {
    private String value;

    public static SqlStatement of(String value) {
        return new SqlStatement(value);
    }

    SqlStatement(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
