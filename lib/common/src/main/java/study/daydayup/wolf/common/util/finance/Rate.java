package study.daydayup.wolf.common.util.finance;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 4:45 下午
 **/
public class Rate {

    public static long calculate(long amount, int ratePerMillion) {
        if (amount <= 0 || ratePerMillion <= 0) {
            return 0;
        }

        BigDecimal nAmount = new BigDecimal(amount);
        BigDecimal nRate   = new BigDecimal(ratePerMillion)
                .divide(new BigDecimal(1000000));

        BigDecimal result = nAmount.multiply(nRate);
        result.setScale(0, BigDecimal.ROUND_HALF_UP);

        return result.longValue();
    }

}
