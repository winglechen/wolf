package com.wolf.dbclient.sql.builder;

import com.wolf.common.lang.io.sql.builder.LimitBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * com.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/23 3:32 下午
 **/
public class LimitBuilderTest {

    @Test
    public void limit() {
        String expect = " LIMIT 10, 10";
        String result = LimitBuilder.limit(10, 10);

        Assert.assertEquals("LimitBuilder.limit fail", expect, result);
    }

    @Test
    public void noOffsetLimit() {
        String expect = " LIMIT 10";
        String result = LimitBuilder.limit(0, 10);

        Assert.assertEquals("LimitBuilder.limit fail", expect, result);
    }
}
