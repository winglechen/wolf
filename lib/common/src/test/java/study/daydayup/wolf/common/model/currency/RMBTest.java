package study.daydayup.wolf.common.model.currency;

import org.junit.Test;
import study.daydayup.wolf.common.lang.enums.currency.RMBEnum;
import study.daydayup.wolf.common.model.type.number.currency.RMB;

import static org.junit.Assert.*;

/**
 * study.daydayup.wolf.model.type.currency
 *
 * @author Wingle
 * @since 2019/12/19 3:57 下午
 **/
public class RMBTest {

    @Test
    public void toYuan() {
        RMB r = new RMB(3, RMBEnum.YUAN);

        long vY = r.toYuan().toLong();
        assertEquals("convert to yuan fail", 3, vY);

        long vJ = r.toJiao().toLong();
        assertEquals("convert to jiao fail", 30, vJ);

        long vF = r.toFen().toLong();
        assertEquals("convert to fen fail", 300, vF);

        long vL = r.toLi().toLong();
        assertEquals("convert to li fail", 3000, vL);

        long vH = r.toHao().toLong();
        assertEquals("convert to hao fail", 30000, vH);

        long vS = r.toSi().toLong();
        assertEquals("convert to si fail", 300000, vS);

    }

    @Test
    public void toSi() {
        RMB r = new RMB(300000, RMBEnum.SI);

        long vY = r.toYuan().toLong();
        assertEquals("convert to yuan fail", 3, vY);

        long vJ = r.toJiao().toLong();
        assertEquals("convert to jiao fail", 30, vJ);

        long vF = r.toFen().toLong();
        assertEquals("convert to fen fail", 300, vF);

        long vL = r.toLi().toLong();
        assertEquals("convert to li fail", 3000, vL);

        long vH = r.toHao().toLong();
        assertEquals("convert to hao fail", 30000, vH);

        long vS = r.toSi().toLong();
        assertEquals("convert to si fail", 300000, vS);

    }

    @Test
    public void toFen() {
        RMB r = new RMB(300, RMBEnum.FEN);

        long vY = r.toYuan().toLong();
        assertEquals("convert to yuan fail", 3, vY);

        long vJ = r.toJiao().toLong();
        assertEquals("convert to jiao fail", 30, vJ);

        long vF = r.toFen().toLong();
        assertEquals("convert to fen fail", 300, vF);

        long vL = r.toLi().toLong();
        assertEquals("convert to li fail", 3000, vL);

        long vH = r.toHao().toLong();
        assertEquals("convert to hao fail", 30000, vH);

        long vS = r.toSi().toLong();
        assertEquals("convert to si fail", 300000, vS);
    }
}