package study.daydayup.wolf.common.io.db;

import java.util.Collection;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:09 下午
 **/
public class Query {
    private Table result;
    private Table table;

    Query(Table table){
        this.table = table;
        this.result = new Table();
    }

    public Query equal(String column, Object value) {
        return this;
    }

    public Query notEqual(String column, Object value) {
        return this;
    }

    public Query isNull(String column, Object value) {
        return this;
    }

    public Query notNull(String column, Object value) {
        return this;
    }

    public Query greaterThan(String column, Object value) {
        return this;
    }

    public Query greaterOrEqual(String column, Object value) {
        return this;
    }

    public Query lessThan(String column, Object value) {
        return this;
    }

    public Query lessOrEqual(String column, Object value) {
        return this;
    }

    public Query between(String column, Object start, Object end) {
        return this;
    }

    public Query notBetween(String column, Object start, Object end) {
        return this;
    }

    public Query in(String column, Collection<Object> values) {
        return this;
    }

}
