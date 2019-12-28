package study.daydayup.wolf.business.trade.api.entity.contract;

import lombok.Data;


/**
 * study.daydayup.wolf.business.trade.api.entity.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:53 下午
 **/
@Data
public class RepaymentTerm {

    private String tradeNo;
    private Long  buyerId;
    private Long  sellerId;

    private Long  LoanAmount;
    private Long  paidAmount;
    private Long  lossAmount;
    private Integer currency;

    private Integer repayStrategy;
    private Integer prepayStrategy;
}
