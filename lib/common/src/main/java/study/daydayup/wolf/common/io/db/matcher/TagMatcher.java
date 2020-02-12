package study.daydayup.wolf.common.io.db.matcher;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.model.type.string.Tag;

/**
 * study.daydayup.wolf.common.io.db.matcher
 *
 * @author Wingle
 * @since 2020/2/11 5:58 下午
 **/
public class TagMatcher extends AbstractMatcher implements Matcher {
    private String tag;

    public void init(@NonNull String column, @NonNull String tag) {
        this.column = column;
        this.tag = tag;
    }

    @Override
    public boolean match(Row row) {
        Object value = row.get(column);
        if (value == null) {
            return false;
        }

        Tag obj;
        if (value instanceof String) {
            obj = new Tag((String)value);
        } else if (value instanceof Tag) {
            obj = (Tag)value;
        } else {
            return false;
        }

        return obj.contains(tag);
    }
}
