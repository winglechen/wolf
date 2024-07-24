package com.wolf.dts.transformer.matcher;

import com.wolf.common.lang.io.db.Row;

/**
 * com.wolf.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 11:44 上午
 **/
public interface Matcher {
    void init(String column);

    void init(String column, Object value);

    boolean match(Row row);
}
