package study.daydayup.wolf.dts.transformation.matcher;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;
import study.daydayup.wolf.common.model.type.string.Tag;

/**
 * study.daydayup.wolf.dts.transformation.matcher
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
        Object col = row.get(column);
        if (col == null) {
            return false;
        }

        Tag obj;
        if (col instanceof String) {
            obj = new Tag((String)col);
        } else if (col instanceof Tag) {
            obj = (Tag)col;
        } else {
            return false;
        }

        return obj.contains(tag);
    }
}
