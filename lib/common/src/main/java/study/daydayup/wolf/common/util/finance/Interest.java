package study.daydayup.wolf.common.util.finance;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 4:45 下午
 **/
public class Interest {
    public static long rate(long amount, int ratePerMillion, int duration) {
        if (amount <= 0 || ratePerMillion <= 0 || duration <= 0) {
            return 0;
        }

        BigDecimal nAmount = new BigDecimal(amount);
        BigDecimal nDuration = new BigDecimal(duration);

        BigDecimal nRate   = new BigDecimal(ratePerMillion)
                .divide(new BigDecimal(1000000));

        BigDecimal interest = nAmount.multiply(nRate).multiply(nDuration);
        BigDecimal result = nAmount.add(interest);
        result.setScale(0, BigDecimal.ROUND_HALF_UP);

        return result.longValue();
    }

    public static long compound(long amount, int ratePerMillion, int duration) {
        if (amount <= 0 || ratePerMillion <= 0 || duration <= 0) {
            return 0;
        }

        BigDecimal nAmount = new BigDecimal(amount);
        BigDecimal nRate   = new BigDecimal(ratePerMillion)
                .divide(new BigDecimal(1000000))
                .add(new BigDecimal(1));

        BigDecimal result = nAmount;

        for (int i = 0; i < duration; i++) {
            result = result.multiply(nRate);
        }

        result.setScale(0, BigDecimal.ROUND_HALF_UP);
        return result.longValue();
    }
}
