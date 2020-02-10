package study.daydayup.wolf.common.io.db;

import lombok.NonNull;
import study.daydayup.wolf.common.model.type.string.Tag;

import java.util.Collection;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:09 下午
 **/
public class Matcher {
    private Row row;

    public Matcher(Row row){
        this.row = row;
    }

    public boolean match() {
        return false;
    }

    public Matcher equal(String column, Object value) {
        return this;
    }

    public Matcher notEqual(String column, Object value) {
        return this;
    }

    public Matcher isNull(String column, Object value) {
        return this;
    }

    public Matcher notNull(String column, Object value) {
        return this;
    }

    public Matcher greaterThan(String column, Object value) {
        return this;
    }

    public Matcher greaterOrEqual(String column, Object value) {
        return this;
    }

    public Matcher lessThan(String column, Object value) {
        return this;
    }

    public Matcher lessOrEqual(String column, Object value) {
        return this;
    }

    public Matcher between(String column, Object start, Object end) {
        return this;
    }

    public Matcher notBetween(String column, Object start, Object end) {
        return this;
    }

    public Matcher in(String column, Collection<Object> values) {
        return this;
    }


    public Matcher containsTag(@NonNull String tags) {
        return containsTag(tags, Table.DEFAULT_TAG_COLUMN);
    }

    public Matcher containsTag(@NonNull String tags, @NonNull String column) {
        Object value = row.get(column);
        if (!(value instanceof Tag)) {
            return this;
        }

        return this;
    }

}
