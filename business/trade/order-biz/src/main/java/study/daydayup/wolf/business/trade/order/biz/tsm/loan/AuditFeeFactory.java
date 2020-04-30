package study.daydayup.wolf.business.trade.order.biz.tsm.loan;

import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.event.base.CancelEvent;
import study.daydayup.wolf.business.trade.api.domain.event.base.CompleteEvent;
import study.daydayup.wolf.business.trade.api.domain.event.base.ExpireEvent;
import study.daydayup.wolf.business.trade.api.domain.event.base.PaidEvent;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.api.domain.state.base.*;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMachineFactory;
import study.daydayup.wolf.common.sm.DefaultStateMachine;
import study.daydayup.wolf.common.sm.StateMachine;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm.factory
 *
 * @author Wingle
 * @since 2019/12/17 12:19 下午
 **/
public class AuditFeeFactory implements TradeStateMachineFactory {
    private TradeState waitToPay            = new WaitToPayState();
    private TradeState paid                 = new PaidState();
    private TradeState completed            = new CompletedState();
    private TradeState canceled             = new CanceledState();
    private TradeState expired              = new ExpiredState();

    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        StateMachine<TradeState, TradeEvent> machine = new DefaultStateMachine<TradeState, TradeEvent>(waitToPay)
                .bind(waitToPay, paid, new PaidEvent())
                .bind(paid, completed, new CompleteEvent())

                .bind(waitToPay, expired, new ExpireEvent())
                .bind(waitToPay, canceled, new CancelEvent())
                ;

        return machine;
    }
}
