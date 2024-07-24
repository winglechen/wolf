package com.wolf.dts.transformer.matcher;

import com.wolf.common.lang.io.db.Row;

/**
 * com.wolf.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 5:58 下午
 **/
public class LessMatcher extends AbstractMatcher implements Matcher {
    @Override
    public boolean match(Row row) {
        return false;
    }
}
