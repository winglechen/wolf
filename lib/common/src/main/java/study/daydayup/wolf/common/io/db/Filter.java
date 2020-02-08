package study.daydayup.wolf.common.io.db;

import java.util.Collection;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:09 下午
 **/
public class Filter {
    private Row result;
    private Row row;

    public Filter(Row row){
        this.row = row;
        this.result = new Row();
    }

    public Row execute() {
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
