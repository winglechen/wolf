package study.daydayup.wolf.common.util.finance.installment;


import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * study.daydayup.wolf.common.util.finance
 *
 * @author Wingle
 * @since 2019/12/19 9:59 下午
 **/
public class RateInstallment extends AbstractInstallment {

    public RateInstallment(long total, int nums) {
        if (nums <= 0 || total <= 0) {
            throw  new IllegalArgumentException("installment args can't less than 0");
        }

        this.total = BigDecimal.valueOf(total);
        this.remain = this.total;
        this.nums = nums;
        splitList = new ArrayList<>();
    }

}
