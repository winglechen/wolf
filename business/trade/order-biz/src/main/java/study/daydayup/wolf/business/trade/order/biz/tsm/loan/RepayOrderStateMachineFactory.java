package study.daydayup.wolf.business.trade.order.biz.tsm.loan;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMachineFactory;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMap;
import study.daydayup.wolf.common.sm.StateMachine;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm.factory
 *
 * @author Wingle
 * @since 2019/12/17 12:19 下午
 **/
public class RepayOrderStateMachineFactory implements TradeStateMachineFactory {
    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        return null;
    }

    @Override
    public TradeStateMap getStateMap() {
        return null;
    }
}
