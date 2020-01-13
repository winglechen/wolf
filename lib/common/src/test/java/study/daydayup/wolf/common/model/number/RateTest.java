package study.daydayup.wolf.common.model.number;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.unit.RateEnum;
import study.daydayup.wolf.common.model.type.number.Rate;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.model.type.number
 *
 * @author Wingle
 * @since 2019/12/19 3:37 下午
 **/
public class RateTest {

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

}