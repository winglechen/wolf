package study.daydayup.wolf.common.util.finance.installment;

import study.daydayup.wolf.common.lang.enums.unit.RateEnum;
import study.daydayup.wolf.common.model.type.number.Rate;

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

    public long split(int rate) {
        return split(rate, RateEnum.PER_MILLION);
    }

    public long split(int rate, RateEnum unit) {
        if (0 == rate) {
            return 0;
        }
        return split(new Rate(rate, unit));
    }

    public long split(Rate rate) {
        if (remain.compareTo(BigDecimal.ZERO) <= 0 || null == rate) {
            return 0;
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
        return slice.longValue();
    }

    protected long returnRemain() {
        long result = remain.longValue();
        handleRemain(remain);

        return result;
    }

    protected void handleRemain(BigDecimal result) {
        splitList.add(result.longValue());
        remain = remain.subtract(result);
    }

}
