package study.daydayup.wolf.business.trade.order.biz.tsm;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.common.sm.StateMachine;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm.factory
 *
 * @author Wingle
 * @since 2019/12/17 12:18 下午
 **/
public interface TradeStateMachineFactory {
    StateMachine<TradeState, TradeEvent> create();
}
