package study.daydayup.wolf.common.util.lang;

import study.daydayup.wolf.common.model.type.string.Decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2020/1/16 5:03 下午
 **/
public class DecimalUtil {
    public static BigDecimal scale(BigDecimal num) {
        return scale(num, Decimal.DEFAULT_SCALE);
    }

    public static BigDecimal scale(BigDecimal num, int scale) {
        if (scale <=0 || scale >= 50) {
            return num;
        }

        return num.setScale(scale, RoundingMode.HALF_UP);
    }
}
