package study.daydayup.wolf.business.trade.api.domain.entity.contract;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.entity.trade.TradeStateLog;


/**
 * study.daydayup.wolf.business.trade.api.domain.entity.contract
 *
 * @author Wingle
 * @since 2019/12/13 3:53 下午
 **/
@Data
public class RepaymentTerm {

    protected String tradeNo;
    protected Long  buyerId;
    protected Long  sellerId;

    protected Integer repayStrategy;
    protected Integer prepayStrategy;

    protected Integer state;

    protected Long  LoanAmount;
    protected Long  paidAmount;
    protected Long  lossAmount;
    protected Integer currency;

    protected TradeStateLog stateLog;
}
