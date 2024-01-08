package study.daydayup.wolf.common.util.lang;

import lombok.NonNull;
import study.daydayup.wolf.common.model.type.number.Decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2020/1/16 5:03 下午
 **/
public class DecimalUtil {
    private static final DecimalFormat DECIMAL_6 = new DecimalFormat("### ###.######");
    private static final DecimalFormat DECIMAL_4 = new DecimalFormat("### ###.####");
    private static final DecimalFormat DECIMAL_2 = new DecimalFormat("### ###.##");

    public static BigDecimal scale(BigDecimal num) {
        return scale(num, Decimal.DEFAULT_SCALE);
    }

    public static BigDecimal scale(BigDecimal num, int scale) {
        if (scale <= 0 || scale >= 100) {
            return num;
        }

        return num.setScale(scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal scaleEven(BigDecimal num){
        return num.setScale(Decimal.DEFAULT_SCALE, RoundingMode.HALF_EVEN);
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

    public static String to2point(BigDecimal num) {
        return DECIMAL_2.format(num);
    }

    public static String toString(BigDecimal num) {
        return toString(num, Decimal.DEFAULT_SCALE);
    }

    public static String toString(BigDecimal num, int scale) {
        return scale(num, scale).toString();
    }

    public static long toLong(BigDecimal num) {
        return num.setScale(0, RoundingMode.HALF_UP).longValue();
    }

    public static int toInt(BigDecimal num) {
        return num.setScale(0, RoundingMode.HALF_UP).intValue();
    }

    /**
     * if bigDecimal1 &gt; bigDecimal2 return true
     *
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    public static boolean isGreater(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        Assert.notNull(bigDecimal1);
        Assert.notNull(bigDecimal2);
        return bigDecimal1.compareTo(bigDecimal2) > 0;
    }

    /**
     * if bigDecimal1 &gt;= bigDecimal2 return true
     *
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    public static boolean isGreaterOrEqual(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        Assert.notNull(bigDecimal1);
        Assert.notNull(bigDecimal2);
        return bigDecimal1.compareTo(bigDecimal2) >= 0;
    }

    /**
     * if bigDecimal1 &lt; bigDecimal2 return true
     *
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    public static boolean isLess(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        Assert.notNull(bigDecimal1);
        Assert.notNull(bigDecimal2);
        return bigDecimal1.compareTo(bigDecimal2) < 0;
    }

    /**
     * if bigDecimal1 &lt;= bigDecimal2 return true
     *
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    public static boolean isLessOrEqual(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        Assert.notNull(bigDecimal1);
        Assert.notNull(bigDecimal2);
        return bigDecimal1.compareTo(bigDecimal2) <= 0;
    }

    /**
     * if bigDecimal1 equal to bigDecimal2 return true
     *
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    public static boolean isEqual(BigDecimal bigDecimal1, BigDecimal bigDecimal2) {
        if (bigDecimal1 == bigDecimal2) {
            return true;
        }
        if (bigDecimal1 == null || bigDecimal2 == null) {
            return false;
        }

        return bigDecimal1.compareTo(bigDecimal2) == 0;
    }
}
