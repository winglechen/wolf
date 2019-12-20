package study.daydayup.wolf.common.util.finance.installment;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.unit.RateEnum;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.common.util.finance.installment
 *
 * @author Wingle
 * @since 2019/12/20 10:56 上午
 **/
public class RateInstallmentTest {

    @Test
    public void split() {
        long expect, actual;

        RateInstallment installment = new RateInstallment(1000, 3);

        expect = 330;
        actual = installment.split(33, RateEnum.PER_HUNDRED);
        assertEquals("installment fail", expect, actual);

        expect = 330;
        actual = installment.split(33, RateEnum.PER_HUNDRED);
        assertEquals("installment fail", expect, actual);

        expect = 340;
        actual = installment.split(34, RateEnum.PER_HUNDRED);
        assertEquals("installment fail", expect, actual);
    }

    @Test
    public void moreSplit() {
        long expect, actual;

        RateInstallment installment = new RateInstallment(1000, 3);

        expect = 500;
        actual = installment.split(50, RateEnum.PER_HUNDRED);
        assertEquals("installment fail", expect, actual);

        expect = 500;
        actual = installment.split(50, RateEnum.PER_HUNDRED);
        assertEquals("installment fail", expect, actual);

        expect = 0;
        actual = installment.split(30, RateEnum.PER_HUNDRED);
        assertEquals("installment fail", expect, actual);
    }

    @Test
    public void lessSplit() {
        long expect, actual;

        RateInstallment installment = new RateInstallment(1000, 3);

        expect = 200;
        actual = installment.split(20, RateEnum.PER_HUNDRED);
        assertEquals("installment fail", expect, actual);

        expect = 200;
        actual = installment.split(20, RateEnum.PER_HUNDRED);
        assertEquals("installment fail", expect, actual);

        expect = 600;
        actual = installment.split(30, RateEnum.PER_HUNDRED);
        assertEquals("installment fail", expect, actual);
    }

}