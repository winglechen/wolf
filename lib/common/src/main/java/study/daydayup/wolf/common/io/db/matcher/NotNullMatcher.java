package study.daydayup.wolf.common.io.db.matcher;

import study.daydayup.wolf.common.io.db.Row;

/**
 * study.daydayup.wolf.common.io.db.matcher
 *
 * @author Wingle
 * @since 2020/2/11 5:58 下午
 **/
public class NotNullMatcher extends AbstractMatcher implements Matcher {
    @Override
    public boolean match(Row row) {
        return false;
    }
}
