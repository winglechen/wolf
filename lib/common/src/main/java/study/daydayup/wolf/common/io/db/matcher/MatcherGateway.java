package study.daydayup.wolf.common.io.db.matcher;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.io.db.Table;
import study.daydayup.wolf.common.model.type.string.Tag;

import java.util.Collection;

/**
 * study.daydayup.wolf.common.io.db
 *
 * @author Wingle
 * @since 2019/11/25 9:09 下午
 **/
public class MatcherGateway implements Matcher {
    private Row row;

    public MatcherGateway(){
    }

    public boolean match(Row row) {
        return false;
    }

    public MatcherGateway equal(String column, Object value) {
        return this;
    }

    public MatcherGateway notEqual(String column, Object value) {
        return this;
    }

    public MatcherGateway isNull(String column, Object value) {
        return this;
    }

    public MatcherGateway notNull(String column, Object value) {
        return this;
    }

    public MatcherGateway greaterThan(String column, Object value) {
        return this;
    }

    public MatcherGateway greaterOrEqual(String column, Object value) {
        return this;
    }

    public MatcherGateway lessThan(String column, Object value) {
        return this;
    }

    public MatcherGateway lessOrEqual(String column, Object value) {
        return this;
    }

    public MatcherGateway between(String column, Object start, Object end) {
        return this;
    }

    public MatcherGateway notBetween(String column, Object start, Object end) {
        return this;
    }

    public MatcherGateway in(String column, Collection<Object> values) {
        return this;
    }


    public MatcherGateway containsTag(@NonNull String tags) {
        return containsTag(tags, Table.DEFAULT_TAG_COLUMN);
    }

    public MatcherGateway containsTag(@NonNull String tags, @NonNull String column) {
        Object value = row.get(column);
        if (!(value instanceof Tag)) {
            return this;
        }

        return this;
    }

}
