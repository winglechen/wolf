package study.daydayup.wolf.business.trade.api.domain.vo.buy;

import lombok.Data;
import study.daydayup.wolf.framework.layer.domain.VO;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.trade.api.domain.vo.buy
 *
 * @author Wingle
 * @since 2019/12/18 10:53 上午
 **/
@Data
public class Loan implements VO  {
    private Integer handlingFeeRate;

    private BigDecimal interestRate;
    private BigDecimal penaltyRate;
    private Integer interestUnit;
    private Integer penaltyUnit;

    private Integer period;
    private Integer periodUnit;
    private Integer periodStrategy;

    private Integer repayStrategy;
    private Integer prepayStrategy;
    private Integer amountStrategy;
    private Integer feePayStrategy;
}
