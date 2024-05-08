package com.wolf.framework.rpc;

import org.junit.Test;
import com.wolf.common.lang.exception.api.NullReturnedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * com.wolf.framework.rpc
 *
 * @author Wingle
 * @since 2019/11/6 4:52 下午
 **/
public class ResultTest {
    @Test
    public void test_get_not_null_data_work() {
        Result<String> r1 = new Result<>("hello");

        String expect = "hello";
        String actual = r1.notNullData();

        assertEquals("getNotNullData fail", expect, actual);
    }

    @Test(expected = NullReturnedException.class)
    public void test_get_not_null_data_exception() {
        Result<String> r1 = new Result<>();
        r1.setData(null);
        r1.notNullData();
    }

    @Test
    public void test_extend_work_fine() {
        Result<Object> r = new Result<>();
        assertNull("Result data default value is not null", r.getData());
    }

}
