package com.onedot.win.dts.transformer.matcher;

import com.onedot.win.common.io.db.Row;

/**
 * com.onedot.win.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 5:58 下午
 **/
public class BetweenMatcher extends AbstractMatcher implements Matcher {
    private Object start;
    private Object end;

    public void init(String column, Object start, Object end) {
        this.column = column;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean match(Row row) {
        return false;
    }
}
