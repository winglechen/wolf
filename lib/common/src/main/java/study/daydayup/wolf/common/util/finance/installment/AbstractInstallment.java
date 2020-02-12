package study.daydayup.wolf.common.util.finance.installment;

import study.daydayup.wolf.common.lang.enums.unit.RateEnum;
import study.daydayup.wolf.common.model.type.number.Rate;
import study.daydayup.wolf.common.util.lang.DecimalUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * study.daydayup.wolf.common.util.finance.installment
 *
 * @author Wingle
 * @since 2020/1/13 4:31 下午
 **/
public abstract class AbstractInstallment {
    protected BigDecimal total;
    protected BigDecimal remain;

    protected int nums;
    protected int step;
    protected List<Long> splitList;

    public BigDecimal split(int rate) {
        return split(rate, RateEnum.PER_MILLION);
    }

    public BigDecimal split(int rate, RateEnum unit) {
        if (rate <= 0) {
            return BigDecimal.ZERO;
        }
        return split(new Rate(rate, unit));
    }

    public BigDecimal split(BigDecimal rate, RateEnum unit) {
        if (rate.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return split(new Rate(rate, unit));
    }


    public BigDecimal split(Rate rate) {
        if (remain.compareTo(BigDecimal.ZERO) <= 0 || null == rate) {
            return BigDecimal.ZERO;
        }

        step++;
        if (step == nums) {
            return returnRemain();
        }

        BigDecimal slice = total.multiply(rate.toBigDecimal());
        if (slice.compareTo(remain) >= 0) {
            return returnRemain();
        }

        handleRemain(slice);
        return DecimalUtil.scale(slice);
    }

    protected BigDecimal returnRemain() {
        BigDecimal result = remain;
        handleRemain(remain);

        return DecimalUtil.scale(result);
    }

    protected void handleRemain(BigDecimal result) {
        splitList.add(result.longValue());
        remain = remain.subtract(result);
    }

}
