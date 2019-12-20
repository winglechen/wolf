package study.daydayup.wolf.common.util;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.PeriodStrategyEnum;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util
 *
 * @author Wingle
 * @since 2019/12/20 9:50 上午
 **/
public class PeriodUtilTest {

    @Test
    public void sameDaysBetween() {
        LocalDate start, end;
        int days, expected;

        //case 1 
        start = LocalDate.of(2019, 01, 01);
        end = LocalDate.of(2019, 01, 01);

        expected = 1;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 1;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 1;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 0;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);
    }

    @Test
    public void smallDaysBetween() {
        LocalDate start, end;
        int days, expected;

        //case 1
        start = LocalDate.of(2019, 01, 01);
        end = LocalDate.of(2019, 01, 10);

        expected = 10;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 9;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 9;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 8;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);
    }

    @Test
    public void bigDaysBetween() {
        LocalDate start, end;
        int days, expected;

        //case 1
        start = LocalDate.of(2019, 01, 01);
        end = LocalDate.of(2019, 02, 10);

        expected = 41;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 40;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 40;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 39;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);
    }

    @Test
    public void largeDaysBetween() {
        LocalDate start, end;
        int days, expected;

        //case 1
        start = LocalDate.of(2018, 01, 01);
        end = LocalDate.of(2019, 01, 01);

        expected = 366;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 365;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 365;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = 364;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);
    }

    @Test
    public void negativeDaysBetween() {
        LocalDate start, end;
        int days, expected;

        //case 1
        start = LocalDate.of(2019, 01, 01);
        end = LocalDate.of(2018, 01, 01);

        expected = -366;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = -365;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.CLOSE_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = -365;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_CLOSE);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);

        expected = -364;
        days = PeriodUtil.daysBetween(start, end, PeriodStrategyEnum.OPEN_OPEN);
        assertEquals("same day count daysBetween: close close strategy fail", expected, days);
    }



}