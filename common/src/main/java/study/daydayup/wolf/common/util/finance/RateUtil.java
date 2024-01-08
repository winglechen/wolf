package study.daydayup.wolf.common.util.finance;

import study.daydayup.wolf.common.model.type.number.Rate;
import study.daydayup.wolf.common.model.type.number.Decimal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 4:45 下午
 **/
public class RateUtil {
    public static BigDecimal calculate(BigDecimal amount, BigDecimal percentage) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || percentage.compareTo(BigDecimal.ZERO) <=0) {
            return BigDecimal.ZERO;
        }

        BigDecimal rate = percentage.divide(BigDecimal.valueOf(Rate.HUNDRED), MathContext.DECIMAL32);
        BigDecimal result = amount.multiply(rate);

        return result.setScale(Decimal.DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

    public static long calculate(long amount, int ratePerMillion) {
        if (amount <= 0 || ratePerMillion <= 0) {
            return 0;
        }

        BigDecimal nAmount = new BigDecimal(amount);
        BigDecimal nRate   = new BigDecimal(ratePerMillion)
                .divide(new BigDecimal(Rate.MILLION), MathContext.DECIMAL32);

        BigDecimal result = nAmount.multiply(nRate);
        return result.setScale(Decimal.ZERO_SCALE, RoundingMode.HALF_UP).longValue();
    }

}
