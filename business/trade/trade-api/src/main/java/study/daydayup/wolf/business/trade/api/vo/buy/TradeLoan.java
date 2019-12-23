package study.daydayup.wolf.business.trade.api.vo.buy;

import lombok.Builder;
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
public class TradeLoan implements VO  {
    private int handlingFeeRate;

    private int interestRate;
    private int penaltyRate;
    private int interestUnit;
    private int penaltyUnit;

    private int period;
    private int periodUnit;
    private int periodStrategy;

    private int repayStrategy;
    private int prepayStrategy;
    private int amountStrategy;
    private int feePayStrategy;
}
