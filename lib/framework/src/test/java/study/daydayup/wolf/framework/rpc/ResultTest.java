package study.daydayup.wolf.framework.rpc;

import org.junit.Test;
import study.daydayup.wolf.common.lang.exception.NullReturnedException;

import static org.junit.Assert.*;


/**
 * study.daydayup.wolf.framework.rpc
 *
 * @author Wingle
 * @since 2019/11/6 4:52 下午
 **/
public class ResultTest {
    @Test
    public void test_get_not_null_data_work() {
        Result<String> r1 = new Result<>("hello");

        String expect = "hello";
        String actual = r1.getNotNullData();

        assertEquals("getNotNullData fail", expect, actual);
    }

    @Test(expected = NullReturnedException.class)
    public void test_get_not_null_data_exception() {
        Result<String> r1 = new Result<>();
        r1.setData(null);
        r1.getNotNullData();
    }

    @Test
    public void test_extend_work_fine() {
        Result r = new Result();
        assertNull("Result data default value is not null", r.getData());

        Result<Parent> r1 = new Result<>();
        Child child = new Child();
        r1.setData(child);

        Result<Child> r2 = new Result<>();
        r2 = r;
        r.setData(child);
    }

    public void demo() {
        Result<Account> account = Rpc.call("account");
        Account a = account.getNotNullData();

        Goods goods = Rpc.getGoods();
        Ump ump = Rpc.getUmp();
        Order order = Rpc.getOrder();

    }

    static class Parent{}
    static class Child extends Parent{}

    static class Goods{}
    static class Account{}
    static class Ump{}
    static class Order{}

    static class Rpc {
        public static Result call(String p) {
            return new Result();
        }

        public static Account getAccount(){
            return null;
        }

        public static Goods getGoods() {
            return null;
        }

        public static Ump getUmp() {
            return null;
        }

        public static Order getOrder() {
            return null;
        }
    }
}
