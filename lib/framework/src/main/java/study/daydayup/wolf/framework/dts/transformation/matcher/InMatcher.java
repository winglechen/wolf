package study.daydayup.wolf.framework.dts.transformation.matcher;

import study.daydayup.wolf.common.io.db.Row;

import java.util.Collection;

/**
 * study.daydayup.wolf.framework.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 5:58 下午
 **/
public class InMatcher extends AbstractMatcher implements Matcher {

    public void init(String column, Collection<Object> values) {

    }

    @Override
    public boolean match(Row row) {
        return false;
    }
}
