package com.wolf.common.util.finance.installment;

import org.junit.Test;
import com.wolf.common.lang.enums.unit.RateEnum;

import static org.junit.Assert.*;

/**
 * com.wolf.common.util.finance.installment
 *
 * @author Wingle
 * @since 2019/12/20 11:33 上午
 **/
public class AverageInstallmentTest {

    @Test
    public void split() {
        long expect, actual;

        AverageInstallment installment = new AverageInstallment(1000, 3);

        expect = 330;
        actual = installment.split().longValue();
        assertEquals("average installment fail", expect, actual);

        expect = 330;
        actual = installment.split().longValue();
        assertEquals("average installment fail", expect, actual);

        expect = 340;
        actual = installment.split().longValue();
        assertEquals("average installment fail", expect, actual);
    }
}