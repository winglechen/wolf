package study.daydayup.wolf.common.util.time;

import org.junit.Test;

import java.time.LocalDateTime;
import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/10/23 10:54 下午
 **/
public class DateUtilTest {

    @Test
    public void test_is_same_day_work_fine() {
        LocalDateTime t1, t2;

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2020, 1, 2, 3, 20);
        assertTrue(DateUtil.isSameDay(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2020, 1, 2, 23, 20);
        assertTrue(DateUtil.isSameDay(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2020, 1, 3, 23, 20);
        assertFalse(DateUtil.isSameDay(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2020, 2, 2, 23, 20);
        assertFalse(DateUtil.isSameDay(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2012, 1, 2, 23, 20);
        assertFalse(DateUtil.isSameDay(t1, t2));
    }

    @Test
    public void test_is_same_week_work_fine() {
        LocalDateTime t1, t2;

        t1 = LocalDateTime.of(2019, 12, 28, 10, 20);
        t2 = LocalDateTime.of(2020, 1, 1, 3, 20);
        assertFalse(DateUtil.isSameWeek(t1, t2));

        t1 = LocalDateTime.of(2019, 12, 30, 10, 20);
        t2 = LocalDateTime.of(2020, 1, 1, 3, 20);
        assertFalse(DateUtil.isSameWeek(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2020, 1, 2, 3, 20);
        assertTrue(DateUtil.isSameWeek(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2020, 1, 3, 23, 20);
        assertTrue(DateUtil.isSameWeek(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2020, 1, 5, 23, 20);
        assertTrue(DateUtil.isSameWeek(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2020, 1, 6, 23, 20);
        assertFalse(DateUtil.isSameWeek(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2020, 2, 2, 23, 20);
        assertFalse(DateUtil.isSameWeek(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 2, 10, 20);
        t2 = LocalDateTime.of(2012, 1, 2, 23, 20);
        assertFalse(DateUtil.isSameWeek(t1, t2));


        t1 = LocalDateTime.of(2020, 1, 30, 10, 20);
        t2 = LocalDateTime.of(2020, 2, 1, 23, 20);
        assertTrue(DateUtil.isSameWeek(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 30, 10, 20);
        t2 = LocalDateTime.of(2020, 2, 2, 23, 20);
        assertTrue(DateUtil.isSameWeek(t1, t2));

        t1 = LocalDateTime.of(2020, 1, 30, 10, 20);
        t2 = LocalDateTime.of(2020, 2, 3, 23, 20);
        assertFalse(DateUtil.isSameWeek(t1, t2));
    }
}
