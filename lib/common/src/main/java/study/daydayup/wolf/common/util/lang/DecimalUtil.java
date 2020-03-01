package study.daydayup.wolf.common.util.lang;

import lombok.NonNull;
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
        if (scale <=0 || scale >= 100) {
            return num;
        }

        return num.setScale(scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal add(@NonNull BigDecimal... nums) {
        return add(Decimal.DEFAULT_SCALE, nums);
    }

    public static BigDecimal add(int scale, @NonNull BigDecimal... nums) {
        BigDecimal result = BigDecimal.ZERO;

        for (BigDecimal num : nums) {
            result = result.add(num);
        }

        return scale(result);
    }


    public static long toLong(BigDecimal num) {
        return num.setScale(0, RoundingMode.HALF_UP).longValue();
    }

    public static int toInt(BigDecimal num) {
        return num.setScale(0, RoundingMode.HALF_UP).intValue();
    }
}
