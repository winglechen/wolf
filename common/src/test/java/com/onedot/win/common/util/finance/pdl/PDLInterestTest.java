package com.onedot.win.common.util.finance.pdl;

import org.junit.Test;
import com.onedot.win.common.util.finance.Interest;

import static org.junit.Assert.*;

/**
 * com.onedot.win.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 5:07 下午
 **/
public class PDLInterestTest {

    @Test
    public void rate() {
        long result, expected;

        expected = 769;
        result = Interest.rate(333, 30000, 77);
        assertEquals("Interest rate fail.", expected, result);

        expected = 30;
        result = Interest.rate(100, 30000, 10);
        assertEquals("Interest rate fail.", expected, result);
    }

    @Test
    public void test_actual_case1() {
        long amount, interest, penalty, result, expected;
        int rate, penaltyRate, period;

        amount      = 3000;
        rate        = 1000; //0.1%
        penaltyRate = 20000; //2%
        period    = 7;
        //case 1
        interest = Interest.rate(amount, rate, 2);
        result = amount + interest;
        //System.out.println("2天还：" + result);
        expected = 3006;
        assertEquals("interest cal fail", result, expected);

        //case 2
        interest = Interest.rate(amount, rate, period);
        result = amount + interest;
        //System.out.println("正常还："  + result);
        expected = 3021;
        assertEquals("interest cal fail", result, expected);

        //case 3
        interest  = Interest.rate(amount, rate, period);
        penalty = Interest.rate(amount, penaltyRate, 4, true);
        result  = amount + interest + penalty;
        //System.out.println("逾期4天还：" + result);
        expected = 3261;
        assertEquals("interest cal fail", result, expected);

        //case 4
        interest  = Interest.rate(amount, rate, period);
        penalty = Interest.rate(amount, penaltyRate, 38, true);
        result  = amount + interest + penalty;
        //System.out.println("逾期38天还：" + result);
        expected = 4821;
        assertEquals("interest cal fail", result, expected);
    }

    @Test
    public void test_actual_case2() {
        long amount, interest, penalty, result, expected;
        int rate, penaltyRate, period;

        amount      = 5000;
        rate        = 10000; //0.1%
        penaltyRate = 10000; //2%
        period    = 14;
        //case 1
        interest = Interest.rate(amount, rate, 2);
        result = amount + interest;
        //System.out.println("2天还：" + result);
        expected = 5100;
        assertEquals("interest cal fail", result, expected);

        //case 2
        interest = Interest.rate(amount, rate, period);
        result = amount + interest;
        //System.out.println("正常还："  + result);
        expected = 5700;
        assertEquals("interest cal fail", result, expected);

        //case 3
        result  = Interest.rate(amount, rate, period);
        penalty = Interest.rate(amount, penaltyRate, 4, true);
        result  = amount + result + penalty;
        //System.out.println("逾期4天还：" + result);
        expected = 5900;
        assertEquals("interest cal fail", result, expected);

        //case 4
        result  = Interest.rate(amount, rate, period);
        penalty = Interest.rate(amount, penaltyRate, 42, true);
        result  = amount + result + penalty;
        //System.out.println("逾期2天还：" + result);
        expected = 7800;
        assertEquals("interest cal fail", result, expected);
    }

    @Test
    public void test_actual_case3() {
        long amount, interest, penalty, result, expected;
        int rate, penaltyRate, period;

        amount      = 43000;
        rate        = 1000; //0.1%
        penaltyRate = 20000; //2%
        period    = 7;
        //case 1
        interest = Interest.rate(amount, rate, 2);
        result = amount + interest;
        //System.out.println("2天还：" + result);
        expected = 43086;
        assertEquals("interest cal fail", result, expected);

        //case 2
        interest = Interest.rate(amount, rate, period);
        result = amount + interest;
        //System.out.println("正常还："  + result);
        expected = 43301;
        assertEquals("interest cal fail", result, expected);

        //case 3
        interest  = Interest.rate(amount, rate, period);
        penalty = Interest.rate(amount, penaltyRate, 4, true);
        result  = amount + interest + penalty;
        //System.out.println("逾期4天还：" + result);
        expected = 46741;
        assertEquals("interest cal fail", result, expected);

        //case 4
        interest  = Interest.rate(amount, rate, period);
        penalty = Interest.rate(amount, penaltyRate, 63, true);
        result  = amount + interest + penalty;
        //System.out.println("逾期2天还：" + result);
        expected = 69101;
        assertEquals("interest cal fail", result, expected);
    }

    @Test
    public void compound() {
        long result, expected;

        expected = 410;
        result = Interest.compound(333, 30000, 7);
        assertEquals("compound interest cal fail", result, expected);
    }
}