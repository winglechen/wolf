package study.daydayup.wolf.common.model.currency;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.currency.IndianRupeeEnum;
import study.daydayup.wolf.common.model.type.currency.IndianRupee;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.model.type.currency
 *
 * @author Wingle
 * @since 2019/12/19 4:29 下午
 **/
public class IndianRupeeTest {

    @Test
    public void toRupee() {
        IndianRupee r = new IndianRupee(3, IndianRupeeEnum.RUPEE);

        long v1 = r.toRupee();
        assertEquals("convert to rupee fail", 3, v1);

        long v2 = r.toTenPaise();
        assertEquals("convert to ten paise fail", 30, v2);

        long v3 = r.toPaise();
        assertEquals("convert to paise fail", 300, v3);

        long v4 = r.toTenthPaise();
        assertEquals("convert to tenth paise fail", 3000, v4);

        long v5 = r.toHundredthPaise();
        assertEquals("convert to hundred paise fail", 30000, v5);

        long v6 = r.toThousandthPaise();
        assertEquals("convert to thousandth paise fail", 300000, v6);
    }

    @Test
    public void toPaise() {
        IndianRupee r = new IndianRupee(300, IndianRupeeEnum.PAISE);

        long v1 = r.toRupee();
        assertEquals("convert to rupee fail", 3, v1);

        long v2 = r.toTenPaise();
        assertEquals("convert to ten paise fail", 30, v2);

        long v3 = r.toPaise();
        assertEquals("convert to paise fail", 300, v3);

        long v4 = r.toTenthPaise();
        assertEquals("convert to tenth paise fail", 3000, v4);

        long v5 = r.toHundredthPaise();
        assertEquals("convert to hundred paise fail", 30000, v5);

        long v6 = r.toThousandthPaise();
        assertEquals("convert to thousandth paise fail", 300000, v6);

    }

    @Test
    public void toHundredthPaise() {
        IndianRupee r = new IndianRupee(30000, IndianRupeeEnum.HUNDREDTH_PAISE);

        long v1 = r.toRupee();
        assertEquals("convert to rupee fail", 3, v1);

        long v2 = r.toTenPaise();
        assertEquals("convert to ten paise fail", 30, v2);

        long v3 = r.toPaise();
        assertEquals("convert to paise fail", 300, v3);

        long v4 = r.toTenthPaise();
        assertEquals("convert to tenth paise fail", 3000, v4);

        long v5 = r.toHundredthPaise();
        assertEquals("convert to hundred paise fail", 30000, v5);

        long v6 = r.toThousandthPaise();
        assertEquals("convert to thousandth paise fail", 300000, v6);
    }
}