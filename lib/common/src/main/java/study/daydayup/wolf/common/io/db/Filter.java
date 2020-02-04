package study.daydayup.wolf.common.io.db;

import java.util.Collection;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:09 下午
 **/
public class Filter {
    private Table result;
    private Table table;

    Filter(Table table){
        this.table = table;
        this.result = new Table();
    }

    public Table execute() {
        return result;
    }

    public Filter equal(String column, Object value) {
        return this;
    }

    public Filter notEqual(String column, Object value) {
        return this;
    }

    public Filter isNull(String column, Object value) {
        return this;
    }

    public Filter notNull(String column, Object value) {
        return this;
    }

    public Filter greaterThan(String column, Object value) {
        return this;
    }

    public Filter greaterOrEqual(String column, Object value) {
        return this;
    }

    public Filter lessThan(String column, Object value) {
        return this;
    }

    public Filter lessOrEqual(String column, Object value) {
        return this;
    }

    public Filter between(String column, Object start, Object end) {
        return this;
    }

    public Filter notBetween(String column, Object start, Object end) {
        return this;
    }

    public Filter in(String column, Collection<Object> values) {
        return this;
    }

}
