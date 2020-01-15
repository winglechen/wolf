package study.daydayup.wolf.business.trade.api.domain.entity.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.framework.layer.domain.VO;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;


/**
 * study.daydayup.wolf.business.trade.api.domain.entity.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:54 下午
 **/
@Data
@SuperBuilder
@NoArgsConstructor
public class LoanTerm implements VO {
    protected String tradeNo;
    protected Long  buyerId;
    protected Long  sellerId;

    @DecimalMin("0.0001")
    protected BigDecimal  amount;
    protected BigDecimal  repayAmount;
    protected BigDecimal  lossAmount;
    protected Integer currency;

    protected Integer period;
    protected Integer periodUnit;
    protected Integer periodStrategy;

    protected BigDecimal handlingFee;
    protected BigDecimal handlingFeeRate;
    protected Integer feePayStrategy;

    protected BigDecimal  interest;
    protected BigDecimal interestRate;
    protected Integer interestUnit;

    protected BigDecimal  penalty;
    protected BigDecimal penaltyRate;
    protected Integer penaltyUnit;

    protected Integer repayStrategy;
    protected Integer prepayStrategy;
}
