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
}
