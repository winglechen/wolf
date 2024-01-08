package study.daydayup.wolf.common.model.number;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.unit.RateEnum;
import study.daydayup.wolf.common.model.type.number.Rate;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.model.type.number
 *
 * @author Wingle
 * @since 2019/12/19 3:37 下午
 **/
public class RateUtilTest {

    @Test
    public void toHundred() {
        Rate r = new Rate(3, RateEnum.PER_HUNDRED);

        int vH = r.toHundred().toInt();
        assertEquals("hundred convert fail", 3, vH);

        int vT = r.toThousand().toInt();
        assertEquals("thousand convert fail", 30, vT);

        int vTT = r.toTenThousand().toInt();
        assertEquals("ten thousand convert fail", 300, vTT);

        int vHT = r.toHundredThousand().toInt();
        assertEquals("hundred thousand convert fail", 3000, vHT);

        int vM = r.toMillion().toInt();
        assertEquals("million convert fail", 30000, vM);
    }

    @Test
    public void ToThousand() {
        Rate r = new Rate(30, RateEnum.PER_THOUSAND);

        int vH = r.toHundred().toInt();
        assertEquals("hundred convert fail", 3, vH);

        int vT = r.toThousand().toInt();
        assertEquals("thousand convert fail", 30, vT);

        int vTT = r.toTenThousand().toInt();
        assertEquals("ten thousand convert fail", 300, vTT);

        int vHT = r.toHundredThousand().toInt();
        assertEquals("hundred thousand convert fail", 3000, vHT);

        int vM = r.toMillion().toInt();
        assertEquals("million convert fail", 30000, vM);
    }

    @Test
    public void toMillion() {
        Rate r = new Rate(30000, RateEnum.PER_MILLION);

        int vH = r.toHundred().toInt();
        assertEquals("hundred convert fail", 3, vH);

        int vT = r.toThousand().toInt();
        assertEquals("thousand convert fail", 30, vT);

        int vTT = r.toTenThousand().toInt();
        assertEquals("ten thousand convert fail", 300, vTT);

        int vHT = r.toHundredThousand().toInt();
        assertEquals("hundred thousand convert fail", 3000, vHT);

        int vM = r.toMillion().toInt();
        assertEquals("million convert fail", 30000, vM);
    }

    @Test
    public void toDouble() {
        Rate r = new Rate(345678, RateEnum.PER_MILLION);

        BigDecimal vH = r.toHundred().toBigDecimal();
        assertEquals("hundred convert fail", new BigDecimal("34.5678"), vH);

        String sH0 = r.toHundred().toString(0);
        assertEquals("hundred convert fail", "35", sH0);

        String sH1 = r.toHundred().toString(1);
        assertEquals("hundred convert fail", "34.6", sH1);

        String sH2 = r.toHundred().toString(2);
        assertEquals("hundred convert fail", "34.57", sH2);

        String sH3 = r.toHundred().toString(3);
        assertEquals("hundred convert fail", "34.568", sH3);

        String sH4 = r.toHundred().toString(4);
        assertEquals("hundred convert fail", "34.5678", sH4);

        String sH = r.toHundred().toString();
        assertEquals("hundred convert fail", "34.5678", sH);

        BigDecimal vT = r.toThousand().toBigDecimal();
        assertEquals("thousand convert fail", new BigDecimal("345.6780"), vT);

        BigDecimal vTT = r.toTenThousand().toBigDecimal();
        assertEquals("ten thousand convert fail", new BigDecimal("3456.7800"), vTT);

        BigDecimal vHT = r.toHundredThousand().toBigDecimal();
        assertEquals("hundred thousand convert fail", new BigDecimal("34567.8000"), vHT);

        BigDecimal vM = r.toMillion().toBigDecimal();
        assertEquals("million convert fail", new BigDecimal("345678.0000"), vM);
    }

}