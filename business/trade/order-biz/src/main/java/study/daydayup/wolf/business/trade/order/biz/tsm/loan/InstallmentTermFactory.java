package study.daydayup.wolf.business.trade.order.biz.tsm.loan;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMachineFactory;
import study.daydayup.wolf.common.sm.StateMachine;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm.loan
 *
 * @author Wingle
 * @since 2019/12/26 7:11 下午
 **/
public class InstallmentTermFactory implements TradeStateMachineFactory {
    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        return null;
    }
}
