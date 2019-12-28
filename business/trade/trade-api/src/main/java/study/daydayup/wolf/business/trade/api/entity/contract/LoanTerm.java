package study.daydayup.wolf.business.trade.api.entity.contract;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;



/**
 * study.daydayup.wolf.business.trade.api.entity.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:54 下午
 **/
@Data
@SuperBuilder
@NoArgsConstructor
public class LoanTerm extends ContractTerm {
    protected String tradeNo;
    protected Long  buyerId;
    protected Long  sellerId;

    protected Long  amount;
    protected Integer currency;

    protected Integer period;
    protected Integer periodUnit;
    protected Integer periodStrategy;

    protected Long  handlingFee;
    protected Integer handlingFeeRate;
    protected Integer feePayStrategy;

    protected Long  interest;
    protected Integer interestRate;
    protected Integer interestUnit;

    protected Long  penalty;
    protected Integer penaltyRate;
    protected Integer penaltyUnit;

    protected Integer repayStrategy;
    protected Integer prepayStrategy;
}
