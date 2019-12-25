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
public class LoanContractStateMachineFactory implements TradeStateMachineFactory {
    private StateMachine<TradeState, TradeEvent> machine;

    private TradeState waitToApprove        = new WaitToApproveState();
    private TradeState approved             = new ApprovedState();

    private TradeState loaning              = new LoaningState();
    private TradeState loaned               = new LoanedState();

    private TradeState repaying             = new RepayingState();
    private TradeState installmentOverdue   = new OverdueState();
    private TradeState repaid               = new RepaidState();

    private TradeState completed            = new CompletedState();
    private TradeState overduePaid          = new OverduePaidState();
    private TradeState refused              = new RefusedState();

    public LoanContractStateMachineFactory() {
        machine = new DefaultStateMachine<TradeState, TradeEvent>();
    }

    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        initMachine();
        bindEvents();
        return machine;
    }

    private void initMachine() {
        machine.init(waitToApprove);
    }

    private void bindEvents() {
        machine.bind(waitToApprove, approved, new ApprovalEvent())
                .bind(waitToApprove, refused, new RefuseEvent())
                .bind(approved, loaning, new LoanBeginEvent())
                .bind(loaning, loaned, new LoanSuccessEvent())
                .bind(loaned, repaying, new RepayBeginEvent())
                .bind(repaying, overduePaid, new RepayOverdueEvent())
                .bind(repaying, completed, new RepaySuccessEvent())
                ;
    }

}
