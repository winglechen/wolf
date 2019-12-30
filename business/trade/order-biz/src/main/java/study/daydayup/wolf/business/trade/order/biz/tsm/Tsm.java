package study.daydayup.wolf.business.trade.order.biz.tsm;

import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.exception.UnsupportedTradeTypeException;
import study.daydayup.wolf.business.trade.api.exception.order.TradeStateNotFoundException;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.tsm.loan.*;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.common.util.EnumUtil;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm
 * TODO: store the instance
 * @author Wingle
 * @since 2019/12/17 12:09 下午
 **/
public class Tsm {
    public static StateMachine<TradeState, TradeEvent> create(int tradeType) {
        TradeTypeEnum tradeTypeEnum = EnumUtil.codeOf(tradeType, TradeTypeEnum.class);
        return create(tradeTypeEnum);
    }

    public static StateMachine<TradeState, TradeEvent> create(TradeTypeEnum tradeTypeEnum) {
        TradeStateMachineFactory factory = createFactory(tradeTypeEnum);
        return factory.create();
    }

    public static TradeState getStateByCode(Integer code, Integer tradeType) {
        if (code == null || code < 1 || null == tradeType || tradeType < 1) {
            throw null;
        }

        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(tradeType);
        TradeState state = stateMachine.getStateByCode(code);
        if (state == null) {
            throw new TradeStateNotFoundException(code);
        }

        return state;
    }

    public static TradeState getStateByEvent(Integer tradeType, TradeState state, TradeEvent event) {
        if (null == tradeType || event == null) {
            return null;
        }
        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(tradeType);
        if (state == null) {
            return stateMachine.getInitState();
        }

        return stateMachine.fire(state, event);
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
            case INSTALLMENT_TERM:
                return new InstallmentTermFactory();
            default:
                throw new UnsupportedTradeTypeException(tradeTypeEnum.getCode());
        }
    }
}
