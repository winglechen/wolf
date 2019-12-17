package study.daydayup.wolf.business.trade.order.biz.tsm.base;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMachineFactory;
import study.daydayup.wolf.common.sm.StateMachine;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm.factory
 *
 * @author Wingle
 * @since 2019/12/17 12:20 下午
 **/
public class OrderStateMachineFactory implements TradeStateMachineFactory {
    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        return null;
    }
}
