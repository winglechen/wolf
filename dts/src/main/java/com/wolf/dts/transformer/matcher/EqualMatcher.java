package com.wolf.dts.transformer.matcher;

import com.wolf.common.io.db.Row;
import com.wolf.common.util.lang.BeanUtil;

/**
 * com.wolf.dts.transformation.matcher
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
