package com.onedot.win.common.io.sql.builder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * com.onedot.win.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/23 10:20 上午
 **/
public class FromBuilderTest {

    @Test
    public void from() {
        String table = "trade", alias = "t";

        String expect = " FROM `trade` t";
        String result = FromBuilder.from(table, alias);

        assertEquals("FromBuilder.from fail", expect, result);
    }

    @Test
    public void fromNoAlias() {
        String table = "trade", alias = null;

        String expect = " FROM `trade`";
        String result = FromBuilder.from(table, alias);

        assertEquals("FromBuilder.from fail", expect, result);
    }

    @Test
    public void fromBlankAlias() {
        String table = "trade", alias = " ";

        String expect = " FROM `trade`";
        String result = FromBuilder.from(table, alias);

        assertEquals("FromBuilder.from fail", expect, result);
    }

    @Test
    public void fromEmptyAlias() {
        String table = "trade", alias = "";

        String expect = " FROM `trade`";
        String result = FromBuilder.from(table, alias);

        assertEquals("FromBuilder.from fail", expect, result);
    }
}