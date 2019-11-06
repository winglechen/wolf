package study.daydayup.wolf.common.util;

import org.junit.Test;

import java.util.Date;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/10/23 10:54 下午
 **/
public class DateUtilTest {

    @Test
    public void test_date_is_null_work_fine() {
        System.out.println("world");
        Date d = new Date(0);
        DateUtil.isNull(d);
    }
}
