package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.VO;

import javax.validation.constraints.Min;

/**
 * study.daydayup.wolf.business.trade.api.vo.buy
 *
 * @author Wingle
 * @since 2019/12/18 10:53 上午
 **/
@Data
public class TradeLoan extends VO {
    private long handlingFee;
    private int handlingFeeRate;
    private int duration;
    private int interest;
    private int belatedPayment;

    private int interestUnit;
    private int belatedPaymentUnit;
    private int durationUnit;
    private int durationStrategy;
    private int repayStrategy;
    private int prepayStrategy;
    private int amountStrategy;
    private int feePayStrategy;
}
