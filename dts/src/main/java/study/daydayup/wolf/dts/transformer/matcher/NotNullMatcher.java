package study.daydayup.wolf.dts.transformer.matcher;

import study.daydayup.wolf.common.io.db.Row;

/**
 * study.daydayup.wolf.dts.transformation.matcher
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
