package study.daydayup.wolf.business.trade.order.biz.tsm;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.exception.UnsupportedTradeTypeException;
import study.daydayup.wolf.business.trade.order.biz.tsm.loan.*;
import study.daydayup.wolf.common.util.EnumUtil;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm
 *
 * @author Wingle
 * @since 2019/12/17 12:09 下午
 **/
@Component
//@Scope()
public class Tsm {
    public static TradeStateMachineFactory createFactory(int tradeType) {
        TradeTypeEnum tradeTypeEnum = EnumUtil.codeOf(tradeType, TradeTypeEnum.class);

        switch (tradeTypeEnum) {
            case LOAN_CONTRACT:
                return new LoanContractStateMachineFactory();
            case LOAN_ORDER:
                return new LoanOrderStateMachineFactory();
            case LOAN_PROXY:
                return new LoanProxyStateMachineFactory();
            case REPAY_ORDER:
                return new RepayOrderStateMachineFactory();
            case COLLECTION_ORDER:
                return new CollectionOrderStateMachineFactory();
            default:
                throw new UnsupportedTradeTypeException(tradeType);
        }
    }
}
