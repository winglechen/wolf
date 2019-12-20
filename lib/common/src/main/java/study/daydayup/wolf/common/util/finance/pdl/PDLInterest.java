package study.daydayup.wolf.common.util.finance.pdl;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 4:45 下午
 **/
public class PDLInterest {
    private static final int MAX_INTEREST = 60;

    public static long rate(long amount, int ratePerMillion, int duration) {
        return rate(amount, ratePerMillion, duration, false);
    }

    public static long rate(long amount, int ratePerMillion, int duration, boolean maxCheck) {
        if (amount <= 0 || ratePerMillion <= 0 || duration <= 0) {
            return 0;
        }

        BigDecimal nAmount = new BigDecimal(amount);
        BigDecimal nDuration = new BigDecimal(duration);

        BigDecimal nRate   = new BigDecimal(ratePerMillion)
                .divide(new BigDecimal(1000000));

        BigDecimal interest = nAmount.multiply(nRate).multiply(nDuration);

        if (maxCheck) {
            interest = maxInterestCheck(nAmount, interest);
        }

        interest.setScale(0, BigDecimal.ROUND_HALF_UP);

        return interest.longValue();
    }

    public static long compound(long amount, int ratePerMillion, int duration) {
        if (amount <= 0 || ratePerMillion <= 0 || duration <= 0) {
            return 0;
        }

        BigDecimal nAmount = new BigDecimal(amount);

        BigDecimal nRate   = new BigDecimal(ratePerMillion)
                .divide(new BigDecimal(1000000))
                .add(new BigDecimal(1));
        BigDecimal interest = nRate.pow(duration);

        interest = interest.multiply(nAmount);
        interest = interest.setScale(0, BigDecimal.ROUND_HALF_UP);

        return interest.longValue();
    }

    private static BigDecimal maxInterestCheck(BigDecimal amount, BigDecimal interest) {
        BigDecimal maxInterest = amount.multiply(new BigDecimal(PDLInterest.MAX_INTEREST))
                .divide(new BigDecimal(100));

        if (-1 == interest.compareTo(maxInterest)) {
            return interest;
        }

        return maxInterest;
    }
}
