package study.daydayup.wolf.business.trade.order.biz.tsm.loan;

import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.event.loan.ApprovalEvent;
import study.daydayup.wolf.business.trade.api.event.loan.RefuseEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.api.state.loan.ApprovedState;
import study.daydayup.wolf.business.trade.api.state.loan.RefusedState;
import study.daydayup.wolf.business.trade.api.state.loan.WaitToApproveState;
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

    private TradeState waitToApprove = new WaitToApproveState();
    private TradeState approved = new ApprovedState();
    private TradeState refused = new RefusedState();

    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        machine = new DefaultStateMachine<>();
        initMachine();
        addEvents();

        return machine;
    }

    private void initMachine() {
        machine.init(waitToApprove);
    }

    private void addEvents() {
        machine.add(waitToApprove, approved, new ApprovalEvent());
        machine.add(waitToApprove, refused, new RefuseEvent());
    }
}
