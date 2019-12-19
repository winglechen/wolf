package study.daydayup.wolf.common.util.finance;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 5:07 下午
 **/
public class InterestTest {

    @Test
    public void rate() {
        long result, expected;

        expected = 1102;
        result = Interest.rate(333, 30000, 77);
        assertEquals("Interest rate fail.", expected, result);

        expected = 130;
        result = Interest.rate(100, 30000, 10);
        assertEquals("Interest rate fail.", expected, result);
    }

    @Test
    public void compound() {
        long result, expected;

        expected = 410;
        result = Interest.compound(333, 30000, 7);
    }
}