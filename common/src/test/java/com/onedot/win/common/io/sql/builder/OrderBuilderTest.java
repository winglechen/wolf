package com.onedot.win.common.io.sql.builder;

import org.junit.Test;
import com.onedot.win.common.io.enums.OrderEnum;

import static org.junit.Assert.*;

/**
 * com.onedot.win.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/23 3:36 下午
 **/
public class OrderBuilderTest {

    @Test
    public void orderBy() {
        String expect = " ORDER BY id DESC";
        String result = OrderBuilder.orderBy("id", OrderEnum.DESC, true);

        assertEquals("OrderBuilder.orderBy fail", expect, result);
    }

    @Test
    public void nextOrderBy() {
        String expect = ", id DESC";
        String result = OrderBuilder.orderBy("id", OrderEnum.DESC, false);

        assertEquals("OrderBuilder.orderBy fail", expect, result);
    }

    @Test
    public void ascOrderBy() {
        String expect = ", id ASC";
        String result = OrderBuilder.orderBy("id", OrderEnum.ASC, false);

        assertEquals("OrderBuilder.orderBy fail", expect, result);
    }
}