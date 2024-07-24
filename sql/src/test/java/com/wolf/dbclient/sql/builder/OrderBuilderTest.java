package com.wolf.dbclient.sql.builder;

import com.wolf.common.lang.io.sql.builder.OrderBuilder;
import org.junit.Test;
import com.wolf.common.lang.io.enums.OrderEnum;

import static org.junit.Assert.*;

/**
 * com.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/23 3:36 下午
 **/
public class OrderBuilderTest {

    @Test
    public void orderBy() {
        String expect = " ORDER BY id DESC";
        String result = OrderBuilder.orderBy("id", OrderEnum.DESC, true);

        Assert.assertEquals("OrderBuilder.orderBy fail", expect, result);
    }

    @Test
    public void nextOrderBy() {
        String expect = ", id DESC";
        String result = OrderBuilder.orderBy("id", OrderEnum.DESC, false);

        Assert.assertEquals("OrderBuilder.orderBy fail", expect, result);
    }

    @Test
    public void ascOrderBy() {
        String expect = ", id ASC";
        String result = OrderBuilder.orderBy("id", OrderEnum.ASC, false);

        Assert.assertEquals("OrderBuilder.orderBy fail", expect, result);
    }
}
