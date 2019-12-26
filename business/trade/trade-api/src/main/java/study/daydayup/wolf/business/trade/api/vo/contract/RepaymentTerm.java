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
    private Long  buyerId;
    private Long  sellerId;

    private Long  amount;
    private Integer currency;

    private Integer period;
    private Integer periodUnit;
    private Integer periodStrategy;

    private Long  handlingFee;
    private Integer handlingFeeRate;
    private Integer feePayStrategy;

    private Integer interest;
    private Integer interestRate;
    private Integer interestUnit;

    private Integer penalty;
    private Integer penaltyRate;
    private Integer penaltyUnit;

    private Integer repayStrategy;
    private Integer prepayStrategy;
}
