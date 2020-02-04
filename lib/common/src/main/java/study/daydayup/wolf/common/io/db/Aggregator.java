package study.daydayup.wolf.common.io.db;


import java.math.BigDecimal;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2020/2/4 5:19 下午
 **/
public class Aggregator {
    private Table table;

    Aggregator(Table table) {
        this.table = table;
    }

    public int count() {
        return table.size();
    }

    public Long minId() {
        return 0L;
    }

    public Long maxId() {
        return 0L;
    }

    public Object min(String column) {
        return null;
    }

    public Object max(String column) {
        return null;
    }

    public Object sum(String column) {
        return BigDecimal.ZERO;
    }

    public Object avg(String column) {
        return 0;
    }

}
