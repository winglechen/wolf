package com.wolf.common.model.currency;

import org.junit.Test;
import com.wolf.common.lang.enums.currency.IndianRupeeEnum;
import com.wolf.common.model.type.number.currency.IndianRupee;

import static org.junit.Assert.*;

/**
 * com.wolf.model.type.currency
 *
 * @author Wingle
 * @since 2019/12/19 4:29 下午
 **/
public class IndianRupeeTest {

    @Test
    public void toRupee() {
        IndianRupee r = new IndianRupee(3, IndianRupeeEnum.RUPEE);

        long v1 = r.toRupee().toLong();
        assertEquals("convert to rupee fail", 3, v1);

        long v2 = r.toTenPaise().toLong();
        assertEquals("convert to ten paise fail", 30, v2);

        long v3 = r.toPaise().toLong();
        assertEquals("convert to paise fail", 300, v3);

        long v4 = r.toTenthPaise().toLong();
        assertEquals("convert to tenth paise fail", 3000, v4);

        long v5 = r.toHundredthPaise().toLong();
        assertEquals("convert to hundred paise fail", 30000, v5);

        long v6 = r.toThousandthPaise().toLong();
        assertEquals("convert to thousandth paise fail", 300000, v6);
    }

    @Test
    public void toPaise() {
        IndianRupee r = new IndianRupee(300, IndianRupeeEnum.PAISE);

        long v1 = r.toRupee().toLong();
        assertEquals("convert to rupee fail", 3, v1);

        long v2 = r.toTenPaise().toLong();
        assertEquals("convert to ten paise fail", 30, v2);

        long v3 = r.toPaise().toLong();
        assertEquals("convert to paise fail", 300, v3);

        long v4 = r.toTenthPaise().toLong();
        assertEquals("convert to tenth paise fail", 3000, v4);

        long v5 = r.toHundredthPaise().toLong();
        assertEquals("convert to hundred paise fail", 30000, v5);

        long v6 = r.toThousandthPaise().toLong();
        assertEquals("convert to thousandth paise fail", 300000, v6);

    }

    @Test
    public void toHundredthPaise() {
        IndianRupee r = new IndianRupee(30000, IndianRupeeEnum.HUNDREDTH_PAISE);

        long v1 = r.toRupee().toLong();
        assertEquals("convert to rupee fail", 3, v1);

        long v2 = r.toTenPaise().toLong();
        assertEquals("convert to ten paise fail", 30, v2);

        long v3 = r.toPaise().toLong();
        assertEquals("convert to paise fail", 300, v3);

        long v4 = r.toTenthPaise().toLong();
        assertEquals("convert to tenth paise fail", 3000, v4);

        long v5 = r.toHundredthPaise().toLong();
        assertEquals("convert to hundred paise fail", 30000, v5);

        long v6 = r.toThousandthPaise().toLong();
        assertEquals("convert to thousandth paise fail", 300000, v6);
    }
}