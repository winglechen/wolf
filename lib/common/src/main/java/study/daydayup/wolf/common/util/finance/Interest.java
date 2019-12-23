package study.daydayup.wolf.common.util.finance;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 4:45 下午
 **/
public class Interest {
    private static final int MAX_INTEREST = 60;

    public static long rate(long amount, int ratePerMillion, int period) {
        return rate(amount, ratePerMillion, period, false);
    }

    public static long rate(long amount, int ratePerMillion, int period, boolean maxCheck) {
        if (amount <= 0 || ratePerMillion <= 0 || period <= 0) {
            return 0;
        }

        BigDecimal nAmount = new BigDecimal(amount);
        BigDecimal nperiod = new BigDecimal(period);

        BigDecimal nRate   = new BigDecimal(ratePerMillion)
                .divide(new BigDecimal(1000000));

        BigDecimal interest = nAmount.multiply(nRate).multiply(nperiod);

        if (maxCheck) {
            interest = maxInterestCheck(nAmount, interest);
        }

        interest.setScale(0, BigDecimal.ROUND_HALF_UP);

        return interest.longValue();
    }

    public static long compound(long amount, int ratePerMillion, int period) {
        if (amount <= 0 || ratePerMillion <= 0 || period <= 0) {
            return 0;
        }

        BigDecimal nAmount = new BigDecimal(amount);

        BigDecimal nRate   = new BigDecimal(ratePerMillion)
                .divide(new BigDecimal(1000000))
                .add(new BigDecimal(1));
        BigDecimal interest = nRate.pow(period);

        interest = interest.multiply(nAmount);
        interest = interest.setScale(0, BigDecimal.ROUND_HALF_UP);

        return interest.longValue();
    }

    private static BigDecimal maxInterestCheck(BigDecimal amount, BigDecimal interest) {
        BigDecimal maxInterest = amount.multiply(new BigDecimal(Interest.MAX_INTEREST))
                .divide(new BigDecimal(100));

        if (-1 == interest.compareTo(maxInterest)) {
            return interest;
        }

        return maxInterest;
    }
}
