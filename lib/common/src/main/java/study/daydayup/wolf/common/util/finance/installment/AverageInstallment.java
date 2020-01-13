package study.daydayup.wolf.common.util.finance.installment;

import study.daydayup.wolf.common.lang.enums.unit.RateEnum;
import study.daydayup.wolf.common.model.type.number.Rate;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 9:59 下午
 **/
public class AverageInstallment extends AbstractInstallment {
    private Rate rate;

    public AverageInstallment(long total, int nums) {
        if (nums <= 0 || total <= 0) {
            throw  new IllegalArgumentException("installment args can't less than 0");
        }

        this.total = BigDecimal.valueOf(total);
        this.remain = this.total;
        this.nums = nums;
        splitList = new ArrayList<>();

        initRate();
    }

    private void initRate() {
        BigDecimal r = BigDecimal.valueOf(100);
        r = r.divide(BigDecimal.valueOf(nums), 0, BigDecimal.ROUND_HALF_UP);

        rate = new Rate(r.intValue(), RateEnum.PER_HUNDRED);
    }

    public long split() {
        return split(rate);
    }

}
