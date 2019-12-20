package study.daydayup.wolf.business.trade.api.vo.contract;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * study.daydayup.wolf.business.trade.api.vo.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:53 下午
 **/
@Data
@Builder
public class RepaymentTerm extends ContractTerm {
    private String tradeNo;
    private long buyerId;
    private long sellerId;

    private long amount;
    private long handlingFee;
    private int currency;

    private int handlingFeeRate;

    private int interest;
    private int penalty;
    private int interestUnit;
    private int penaltyUnit;

    private int period;
    private int periodUnit;
    private int periodStrategy;

    private int repayStrategy;
    private int prepayStrategy;
    private int amountStrategy;
    private int feePayStrategy;

    private LocalDateTime effectAt;
    private int version;
}
