package study.daydayup.wolf.business.trade.order.biz.tsm.loan;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.event.base.CancelEvent;
import study.daydayup.wolf.business.trade.api.event.base.CompleteEvent;
import study.daydayup.wolf.business.trade.api.event.base.ExpireEvent;
import study.daydayup.wolf.business.trade.api.event.base.PayEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.api.state.base.*;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMachineFactory;
import study.daydayup.wolf.common.sm.DefaultStateMachine;
import study.daydayup.wolf.common.sm.StateMachine;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm.factory
 *
 * @author Wingle
 * @since 2019/12/17 12:19 下午
 **/
public class LoanOrderFactory implements TradeStateMachineFactory {
    private TradeState waitToPay            = new WaitToPayState();
    private TradeState paid                 = new PaidState();
    private TradeState completed            = new CompletedState();
    private TradeState canceled             = new CanceledState();
    private TradeState expired              = new ExpiredState();

    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        StateMachine<TradeState, TradeEvent> machine = new DefaultStateMachine<TradeState, TradeEvent>(waitToPay)
                .bind(waitToPay, paid, new PayEvent())
                .bind(paid, completed, new CompleteEvent())

                .bind(waitToPay, expired, new ExpireEvent())
                .bind(waitToPay, canceled, new CancelEvent())
                ;

        return machine;
    }

}
