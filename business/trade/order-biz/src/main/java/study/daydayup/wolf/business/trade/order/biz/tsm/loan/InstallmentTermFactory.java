package study.daydayup.wolf.business.trade.order.biz.tsm.loan;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.event.base.CompleteEvent;
import study.daydayup.wolf.business.trade.api.event.loan.MarkLossEvent;
import study.daydayup.wolf.business.trade.api.event.loan.collection.ConfirmCollectionEvent;
import study.daydayup.wolf.business.trade.api.event.loan.loan.LoanSuccessEvent;
import study.daydayup.wolf.business.trade.api.event.loan.repay.PrepayInstallmentEvent;
import study.daydayup.wolf.business.trade.api.event.loan.repay.RepayDueEvent;
import study.daydayup.wolf.business.trade.api.event.loan.repay.RepayOverdueEvent;
import study.daydayup.wolf.business.trade.api.event.loan.repay.RepaySuccessEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.api.state.base.CompletedState;
import study.daydayup.wolf.business.trade.api.state.base.PaidState;
import study.daydayup.wolf.business.trade.api.state.loan.CloseAsLossState;
import study.daydayup.wolf.business.trade.api.state.loan.OverduePaidState;
import study.daydayup.wolf.business.trade.api.state.loan.PrepaidState;
import study.daydayup.wolf.business.trade.api.state.loan.collection.PaidByCollectionState;
import study.daydayup.wolf.business.trade.api.state.loan.repay.DueState;
import study.daydayup.wolf.business.trade.api.state.loan.repay.EffectedState;
import study.daydayup.wolf.business.trade.api.state.loan.repay.OverdueState;
import study.daydayup.wolf.business.trade.api.state.loan.repay.WaitToEffectState;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMachineFactory;
import study.daydayup.wolf.common.sm.DefaultStateMachine;
import study.daydayup.wolf.common.sm.StateMachine;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm.loan
 *
 * @author Wingle
 * @since 2019/12/26 7:11 下午
 **/
public class InstallmentTermFactory implements TradeStateMachineFactory {
    private TradeState waitToEffect         = new WaitToEffectState();
    private TradeState effected             = new EffectedState();
    private TradeState due                  = new DueState();
    private TradeState overdue              = new OverdueState();
    private TradeState paid                 = new PaidState();
    private TradeState prepaid              = new PrepaidState();

    private TradeState overduePaid          = new OverduePaidState();
    private TradeState paidByCollection     = new PaidByCollectionState();
    private TradeState closeAsLoss          = new CloseAsLossState();

    private TradeState completed            = new CompletedState();

    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        return new DefaultStateMachine<TradeState, TradeEvent>(waitToEffect)
                .bind(waitToEffect, effected, new LoanSuccessEvent())

                .bind(effected, due, new RepayDueEvent())
                .bind(effected, overdue, new RepayOverdueEvent())
                .bind(due, overdue, new RepayOverdueEvent())

                .bind(effected, paid, new RepaySuccessEvent())
                .bind(effected, prepaid, new PrepayInstallmentEvent())
                .bind(due, paid, new RepaySuccessEvent())
                .bind(due, prepaid, new PrepayInstallmentEvent())

                .bind(overdue, overduePaid, new RepayOverdueEvent())
                .bind(overdue, paidByCollection, new ConfirmCollectionEvent())
                .bind(overdue, closeAsLoss, new MarkLossEvent())

                .bind(paid, completed, new CompleteEvent())
                .bind(prepaid, completed, new CompleteEvent())

                ;
    }
}
