package com.onedot.win.dts.transformer.matcher;

import com.onedot.win.common.io.db.Row;
import com.onedot.win.common.util.lang.BeanUtil;

/**
 * com.onedot.win.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 5:58 下午
 **/
public class EqualMatcher extends AbstractMatcher implements Matcher {
    @Override
    public boolean match(Row row) {
        Object col = row.get(column);
        return BeanUtil.equals(col, value);
    }
}
