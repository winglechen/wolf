package study.daydayup.wolf.common.util.db;

/**
 * study.daydayup.wolf.common.util.db
 *
 * @author Wingle
 * @since 2019/11/27 9:37 上午
 **/
public class DTS {
    private Table source;

    public DTS from(String sql) {
        return from(sql, null);
    }

    public DTS from(String sql, Object parameter) {
        sql = "select count(*) as xxCount, sum(money) as amount from xxx where xxx";
        return this;
    }

    public DTS to(String sql) {
        return to(sql, null);
    }

    public DTS to(String sql, Object parameter) {
        sql = "update xxx set a=b, b=c";
        return this;
    }
}
