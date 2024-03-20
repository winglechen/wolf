package com.onedot.win.common.io.db;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * com.onedot.win.common.io.db
 *
 * @author Wingle
 * @since 2020/10/9 10:36 下午
 **/
public class TableTest {

    @Test
    public void cloneTest() {
        Table t1 = createTable(10);
        Table t2 = t1.clone();
        assertEquals("Table.clone fail", t1.size(), t2.size());

        Row tRow1 = t1.get(0);
        Row tRow2 = t2.get(0);
        assertEquals("Table.clone fail", tRow1.get("name"), "name:0");
        assertEquals("Table.clone fail", tRow1.get("name"), tRow2.get("name"));

        tRow2.put("name", "newName");
        assertNotEquals("Table.clone fail", "newName", tRow1.get("name"));
        assertEquals("Table.clone fail", "newName", tRow2.get("name"));
    }

    private Table createTable(int num) {
        Table t = new Table();

        for (int i = 0; i < num; i++) {
            t.addRow(createRow(i));
        }

        return t;
    }

    private Row createRow(int no) {
        Row r = new Row();
        r.put("name", "name:" + no);
        r.put("age", no);

        return r;
    }
}