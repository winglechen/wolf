package study.daydayup.wolf.business.trade.order.biz.tsm.loan;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.event.loan.*;
import study.daydayup.wolf.business.trade.api.event.loan.repay.RepayBeginEvent;
import study.daydayup.wolf.business.trade.api.event.loan.repay.RepayOverdueEvent;
import study.daydayup.wolf.business.trade.api.event.loan.repay.RepaySuccessEvent;
import study.daydayup.wolf.business.trade.api.event.loan.loan.LoanBeginEvent;
import study.daydayup.wolf.business.trade.api.event.loan.loan.LoanSuccessEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.api.state.base.CompletedState;
import study.daydayup.wolf.business.trade.api.state.loan.*;
import study.daydayup.wolf.business.trade.api.state.loan.contract.*;
import study.daydayup.wolf.business.trade.api.state.loan.repay.OverdueState;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMachineFactory;
import study.daydayup.wolf.common.sm.DefaultStateMachine;
import study.daydayup.wolf.common.sm.StateMachine;

/**
 * study.daydayup.wolf.business.trade.order.biz.tsm.factory
 *
 * @author Wingle
 * @since 2019/12/17 12:19 下午
 **/
public class LoanContractFactory implements TradeStateMachineFactory {
    private StateMachine<TradeState, TradeEvent> machine;

    private TradeState waitToApprove        = new WaitToApproveState();
    private TradeState approved             = new ApprovedState();

    private TradeState loaning              = new LoaningState();
    private TradeState loaned               = new LoanedState();

    private TradeState repaying             = new RepayingState();
    private TradeState overdue              = new OverdueState();
    private TradeState repaid               = new RepaidState();

    private TradeState completed            = new CompletedState();
    private TradeState overduePaid          = new OverduePaidState();
    private TradeState refused              = new RefusedState();

    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        machine = new DefaultStateMachine<TradeState, TradeEvent>(waitToApprove)
                .bind(waitToApprove, approved, new ApproveEvent())
                .bind(waitToApprove, refused, new RefuseEvent())

                .bind(approved, loaning, new LoanBeginEvent())
                .bind(approved, loaned, new LoanSuccessEvent())
                .bind(loaning, loaned, new LoanSuccessEvent())

                .bind(loaned, repaying, new RepayBeginEvent())
                .bind(loaned, overdue, new RepayOverdueEvent())
                .bind(loaned, completed, new RepaySuccessEvent())

                .bind(repaying, overdue, new RepayOverdueEvent())
                .bind(repaying, completed, new RepaySuccessEvent())

                .bind(overdue, overduePaid, new RepayOverdueEvent())
                ;

        return machine;
    }

}
