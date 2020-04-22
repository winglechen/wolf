package study.daydayup.wolf.business.trade.order.biz.tsm;

import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.exception.UnsupportedTradeTypeException;
import study.daydayup.wolf.business.trade.api.domain.exception.order.TradeStateNotFoundException;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.framework.util.LocaleUtil;
import study.daydayup.wolf.business.trade.order.biz.tsm.loan.*;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.common.util.lang.EnumUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm
 * TODO: store the instance
 * @author Wingle
 * @since 2019/12/17 12:09 下午
 **/
public class Tsm {
    private static final String STATE_LANG_PREFIX = "trade.";
    private static final String STATE_NS_PREFIX = "study.daydayup.wolf.business.trade.api.domain.";
    private static final String STATE_NS_SUFFIX = "State";

    public static StateMachine<TradeState, TradeEvent> create(int tradeType) {
        TradeTypeEnum tradeTypeEnum = EnumUtil.codeOf(tradeType, TradeTypeEnum.class);
        return create(tradeTypeEnum);
    }

    public static StateMachine<TradeState, TradeEvent> create(TradeTypeEnum tradeTypeEnum) {
        TradeStateMachineFactory factory = createFactory(tradeTypeEnum);
        return factory.create();
    }

    public static TradeState getStateByCode(Integer code, TradeTypeEnum tradeType) {
       return getStateByCode(code, tradeType.getCode());
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

        return addLocaleInfo(state);
    }

    private static TradeState addLocaleInfo(@NonNull TradeState state) {
        String stateKey = state.getClass().getName();
        stateKey = StringUtil.ltrim(stateKey, STATE_NS_PREFIX);
        stateKey = StringUtil.rtrim(stateKey, STATE_NS_SUFFIX);
        stateKey = StringUtil.join(STATE_LANG_PREFIX, stateKey);
        stateKey = StringUtil.lcWords(stateKey, ".");

        String stateName = LocaleUtil.get(stateKey);
        if (stateName != null) {
            state.setName(stateName);
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

    public static TradeState getInitState(Integer tradeType) {
        if (tradeType == null) {
            return null;
        }
        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(tradeType);
        return stateMachine.getInitState();
    }

    private static TradeStateMachineFactory createFactory(TradeTypeEnum tradeTypeEnum) {
        switch (tradeTypeEnum) {
            case LOAN_CONTRACT:
                return new LoanContractFactory();
            case LOAN_ORDER:
                return new LoanOrderFactory();
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


    public static void main(String[] args) {
        String stateKey = "study.daydayup.wolf.business.trade.api.domain.state.loan.contract.ApprovedState";

        stateKey = StringUtil.ltrim(stateKey, STATE_NS_PREFIX);
        stateKey = StringUtil.rtrim(stateKey, STATE_NS_SUFFIX);
        stateKey = StringUtil.join(STATE_LANG_PREFIX, stateKey);

        System.out.println(stateKey);

    }
}
