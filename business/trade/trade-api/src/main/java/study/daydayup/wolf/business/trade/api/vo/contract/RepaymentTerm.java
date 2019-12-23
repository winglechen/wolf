package study.daydayup.wolf.business.trade.api.vo.contract;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * study.daydayup.wolf.business.trade.api.vo.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:53 下午
 **/
@Data
public class RepaymentTerm {

    private String tradeNo;
    private long buyerId;
    private long sellerId;

    private long amount;
    private int currency;

    private int period;
    private int periodUnit;
    private int periodStrategy;

    private long handlingFee;
    private int handlingFeeRate;
    private int feePayStrategy;

    private int interest;
    private int interestRate;
    private int interestUnit;

    private int penalty;
    private int penaltyRate;
    private int penaltyUnit;

    private int repayStrategy;
    private int prepayStrategy;
}
