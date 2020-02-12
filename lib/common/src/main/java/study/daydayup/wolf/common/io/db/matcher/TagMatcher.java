package study.daydayup.wolf.common.io.db.matcher;

import lombok.NonNull;
import study.daydayup.wolf.common.io.db.Row;

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
        return false;
    }
}
