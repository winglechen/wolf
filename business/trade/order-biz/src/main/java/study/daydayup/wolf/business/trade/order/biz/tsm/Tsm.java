package study.daydayup.wolf.business.trade.order.biz.tsm;

import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.exception.UnsupportedTradeTypeException;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.tsm.loan.*;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.common.util.EnumUtil;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm
 *
 * @author Wingle
 * @since 2019/12/17 12:09 下午
 **/
public class Tsm {
    public StateMachine<TradeState, TradeEvent> create(int tradeType) {
        TradeTypeEnum tradeTypeEnum = EnumUtil.codeOf(tradeType, TradeTypeEnum.class);
        return create(tradeTypeEnum);
    }

    public StateMachine<TradeState, TradeEvent> create(TradeTypeEnum tradeTypeEnum) {
        TradeStateMachineFactory factory = createFactory(tradeTypeEnum);
        return factory.create();
    }

    private static TradeStateMachineFactory createFactory(TradeTypeEnum tradeTypeEnum) {
        switch (tradeTypeEnum) {
            case LOAN_CONTRACT:
                return new LoanContractFactory();
            case LOAN_ORDER:
                return new LoanOrderFactory();
            case LOAN_PROXY:
                return new LoanProxyFactory();
            case REPAY_ORDER:
                return new RepayOrderFactory();
            case COLLECTION_ORDER:
                return new CollectionOrderFactory();
            default:
                throw new UnsupportedTradeTypeException(tradeTypeEnum.getCode());
        }
    }
}
