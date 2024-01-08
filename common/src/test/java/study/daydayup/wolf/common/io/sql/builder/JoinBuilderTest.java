package study.daydayup.wolf.common.io.sql.builder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.io.sql.builder
 *
 * @author Wingle
 * @since 2020/9/23 1:23 下午
 **/
public class JoinBuilderTest {

    @Test
    public void join() {
        String table = "trade";
        String alias = "t";
        String on = "t.order_no = p.order_no";

        String expect = " trade t ON t.order_no = p.order_no";
        String result = JoinBuilder.join(table, alias, on);

        assertEquals("JoinBuilder.join fail", expect, result);
    }

    @Test
    public void noAliasJoin() {
        String table = "trade";
        String alias = null;
        String on = "t.order_no = p.order_no";

        String expect = " trade ON t.order_no = p.order_no";
        String result = JoinBuilder.join(table, alias, on);

        assertEquals("JoinBuilder.join fail", expect, result);
    }

    @Test
    public void emptyAliasJoin() {
        String table = "trade";
        String alias = "";
        String on = "t.order_no = p.order_no";

        String expect = " trade ON t.order_no = p.order_no";
        String result = JoinBuilder.join(table, alias, on);

        assertEquals("JoinBuilder.join fail", expect, result);
    }

    @Test
    public void blankAliasJoin() {
        String table = "trade";
        String alias = " ";
        String on = "t.order_no = p.order_no";

        String expect = " trade ON t.order_no = p.order_no";
        String result = JoinBuilder.join(table, alias, on);

        assertEquals("JoinBuilder.join fail", expect, result);
    }

}