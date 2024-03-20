package com.onedot.win.dts.transformer.matcher;

import com.onedot.win.common.io.db.Row;

/**
 * com.onedot.win.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 11:44 上午
 **/
public interface Matcher {
    void init(String column);

    void init(String column, Object value);

    boolean match(Row row);
}
