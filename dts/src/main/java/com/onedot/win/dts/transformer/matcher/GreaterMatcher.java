package com.onedot.win.dts.transformer.matcher;

import com.onedot.win.common.io.db.Row;

import java.math.BigInteger;

import com.onedot.win.common.lang.exception.lang.IllegalArgumentException;


/**
 * com.onedot.win.dts.transformation.matcher
 *
 * @author Wingle
 * @since 2020/2/11 5:58 下午
 **/
public class GreaterMatcher extends AbstractMatcher implements Matcher {
    @Override
    public boolean match(Row row) {
        Object col = row.get(column);
        if (col instanceof Integer && value instanceof Integer) {
            return (Integer) col > (Integer) value;
        }
        if (col instanceof BigInteger && value instanceof Integer) {
            return ((BigInteger) col).compareTo(BigInteger.valueOf((Integer) value)) > 0;
        }
        if (col instanceof BigInteger && value instanceof Long) {
            return ((BigInteger) col).compareTo(BigInteger.valueOf((Long) value)) > 0;
        }

        if (col instanceof Long && value instanceof Integer) {
            return (Long) col > (Integer) value;
        }

        throw new IllegalArgumentException("check type, maybe need to update framework");
    }
}
