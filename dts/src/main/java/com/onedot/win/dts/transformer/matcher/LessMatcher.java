package com.onedot.win.dts.transformer.matcher;

import com.onedot.win.common.io.db.Row;

/**
 * com.onedot.win.dts.transformation.matcher
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
