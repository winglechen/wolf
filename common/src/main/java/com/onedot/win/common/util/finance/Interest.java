package com.onedot.win.common.util.finance;

import com.onedot.win.common.lang.enums.unit.RateEnum;
import com.onedot.win.common.model.type.number.Rate;
import com.onedot.win.common.model.type.number.Decimal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * com.onedot.win.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 4:45 下午
 **/
public class Interest {
    public static final int MAX_INTEREST = 60;

    public static BigDecimal rate(BigDecimal amount, BigDecimal percentage, int period) {
        return rate(amount, percentage, period, false);
    }

    public static BigDecimal rate(BigDecimal amount, BigDecimal percentage, int period, boolean maxCheck) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || percentage.compareTo(BigDecimal.ZERO) <=0 || period <= 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal rate = percentage.divide(BigDecimal.valueOf(Rate.HUNDRED), MathContext.DECIMAL32);
        BigDecimal interest = amount.multiply(rate).multiply(BigDecimal.valueOf(period));

        if (maxCheck) {
            interest = maxInterestCheck(amount, interest);
        }

        return interest.setScale(Decimal.DEFAULT_SCALE, RoundingMode.HALF_UP);
    }

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
                .divide(RateEnum.PER_MILLION.getBase(), MathContext.DECIMAL32);

        BigDecimal interest = nAmount.multiply(nRate).multiply(nperiod);

        if (maxCheck) {
            interest = maxInterestCheck(nAmount, interest);
        }

        return interest.setScale(Decimal.ZERO_SCALE, RoundingMode.HALF_UP).longValue();
    }

    public static long compound(long amount, int ratePerMillion, int period) {
        if (amount <= 0 || ratePerMillion <= 0 || period <= 0) {
            return 0;
        }

        BigDecimal nAmount = new BigDecimal(amount);
        BigDecimal nRate   = new BigDecimal(ratePerMillion)
                .divide(RateEnum.PER_MILLION.getBase(), MathContext.DECIMAL32)
                .add(new BigDecimal(1));

        BigDecimal interest = nRate.pow(period);
        interest = interest.multiply(nAmount);

        return interest.setScale(Decimal.ZERO_SCALE, BigDecimal.ROUND_HALF_UP).longValue();
    }

    private static BigDecimal maxInterestCheck(BigDecimal amount, BigDecimal interest) {
        BigDecimal maxInterest = amount.multiply(new BigDecimal(Interest.MAX_INTEREST))
                .divide(RateEnum.PER_HUNDRED.getBase(), BigDecimal.ROUND_HALF_UP);

        if (interest.compareTo(maxInterest) < 0) {
            return interest;
        }

        return maxInterest;
    }
}
