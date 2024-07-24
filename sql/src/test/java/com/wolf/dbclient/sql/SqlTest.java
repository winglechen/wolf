package com.wolf.dbclient.sql;

import com.wolf.common.lang.io.sql.Sql;
import org.junit.Test;
import com.wolf.common.lang.io.enums.OrderEnum;
import com.wolf.common.lang.io.sql.builder.SqlBuilder;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * com.wolf.common.io.sql
 *
 * @author Wingle
 * @since 2021/11/8 下午9:54
 **/
public class SqlTest {

    @Test
    public void count() {
    }

    @Test
    public void testCount() {
    }

    @Test
    public void exists() {
    }

    @Test
    public void testExists() {
    }

    @Test
    public void select() {
        SqlBuilder sqlBuilder = Sql.select("a, b, c")
                .from("table")
                .where("a", "in", Arrays.asList(1, 2, 3))
                .and("b = ?", 4)
                .and("c > 4")
                .orderBy("id", OrderEnum.DESC)
                .limit(20, 10);


        Assert.assertTrue("sql build fail", sqlBuilder.getSql().contains("SELECT `a`,`b`,`c`"));
        Assert.assertTrue("sql build fail", sqlBuilder.getSql().contains("FROM `table`"));
        Assert.assertTrue("sql build fail", sqlBuilder.getSql().contains("WHERE `a` IN ( ?, ?, ? )"));
        Assert.assertTrue("sql build fail", sqlBuilder.getSql().contains("AND b = ?"));
        Assert.assertTrue("sql build fail", sqlBuilder.getSql().contains("AND c > 4"));
        Assert.assertTrue("sql build fail", sqlBuilder.getSql().contains("ORDER BY id DESC"));
        Assert.assertTrue("sql build fail", sqlBuilder.getSql().contains("LIMIT 20, 10"));

        Assert.assertTrue("sql build fail", sqlBuilder.getPreparedDataList().contains(1));
        Assert.assertTrue("sql build fail", sqlBuilder.getPreparedDataList().contains(2));
        Assert.assertTrue("sql build fail", sqlBuilder.getPreparedDataList().contains(3));
        Assert.assertTrue("sql build fail", sqlBuilder.getPreparedDataList().contains(4));
    }

    @Test
    public void testSelect() {
    }

    @Test
    public void testSelect1() {
    }

    @Test
    public void testSelect2() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void testInsert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void scan() {
    }

    @Test
    public void testScan() {
    }

    @Test
    public void testScan1() {
    }
}
