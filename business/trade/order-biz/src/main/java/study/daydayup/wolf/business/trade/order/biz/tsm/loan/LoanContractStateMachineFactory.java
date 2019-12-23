package study.daydayup.wolf.business.trade.order.biz.tsm.loan;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.event.loan.*;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.api.state.base.CompetedState;
import study.daydayup.wolf.business.trade.api.state.loan.*;
import study.daydayup.wolf.business.trade.api.state.loan.contract.*;
import study.daydayup.wolf.business.trade.order.biz.tsm.DefaultTradeStateMap;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMachineFactory;
import study.daydayup.wolf.business.trade.order.biz.tsm.TradeStateMap;
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
    private TradeStateMap stateMap;

    private TradeState waitToApprove        = new WaitToApproveState();
    private TradeState approved             = new ApprovedState();

    private TradeState loaning              = new LoaningState();
    private TradeState loaned               = new LoanedState();

    private TradeState repaying             = new RepayingState();
    private TradeState repayOverdue         = new RepayOverdueState();
    private TradeState installmentOverdue   = new OverdueState();
    private TradeState repaid               = new RepaidState();

    private TradeState completed            = new CompetedState();
    private TradeState overduePaid          = new OverduePaidState();
    private TradeState refused              = new RefusedState();

    public LoanContractStateMachineFactory() {
        machine = new DefaultStateMachine<TradeState, TradeEvent>();
        stateMap = new DefaultTradeStateMap();

        registerState();
    }

    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        initMachine();
        addEvents();
        return machine;
    }

    @Override
    public TradeStateMap getStateMap() {
        return stateMap;
    }

    private void initMachine() {
        machine.init(waitToApprove);
    }

    private void registerState() {
        stateMap.addState(waitToApprove)
                .addState(refused)
                .addState(approved)

                .addState(loaning)
                .addState(loaned)

                .addState(repaying)
                .addState(repaid)
                .addState(repayOverdue)
                .addState(installmentOverdue)

                .addState(overduePaid)
                .addState(completed);
    }

    private void addEvents() {
        machine.add(waitToApprove, approved, new ApprovalEvent())
                .add(waitToApprove, refused, new RefuseEvent())
                .add(approved, loaning, new LoanBeginEvent())
                .add(loaning, loaned, new LoanSuccessEvent())
                .add(loaned, repaying, new RepayBeginEvent())
                .add(repaying, repayOverdue, new RepayOverDueEvent())
                .add(repaying, completed, new RepaySuccessEvent())
                .add(repayOverdue, overduePaid, new RepaySuccessEvent())
                ;
    }

}
