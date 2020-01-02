package study.daydayup.wolf.business.trade.order.biz.tsm.loan;

import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.event.base.CompleteEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.*;
import study.daydayup.wolf.business.trade.api.domain.event.loan.collection.ConfirmCollectionEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.repay.PrepayAllEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.repay.RepayBeginEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.repay.RepayOverdueEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.repay.RepaySuccessEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.loan.LoanBeginEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.loan.LoanSuccessEvent;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.api.domain.state.base.CompletedState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.*;
import study.daydayup.wolf.business.trade.api.domain.state.loan.collection.PaidByCollectionState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.*;
import study.daydayup.wolf.business.trade.api.domain.state.loan.repay.OverdueState;
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
    private TradeState prepaid              = new PrepaidState();

    private TradeState completed            = new CompletedState();
    private TradeState overduePaid          = new OverduePaidState();
    private TradeState paidByCollection     = new PaidByCollectionState();

    private TradeState refused              = new RefusedState();
    private TradeState closeAsLoss          = new CloseAsLossState();

    @Override
    public StateMachine<TradeState, TradeEvent> create() {
        //TODO: Prepay support
        machine = new DefaultStateMachine<TradeState, TradeEvent>(waitToApprove)
                .bind(waitToApprove, approved, new ApproveEvent())
                .bind(waitToApprove, refused, new RefuseEvent())

                .bind(approved, loaning, new LoanBeginEvent())
                .bind(approved, loaned, new LoanSuccessEvent())
                .bind(loaning, loaned, new LoanSuccessEvent())

                .bind(loaned, repaying, new RepayBeginEvent())
                .bind(loaned, overdue, new RepayOverdueEvent())
                .bind(loaned, repaid, new RepaySuccessEvent())
                .bind(loaned, prepaid, new PrepayAllEvent())

                .bind(repaying, overdue, new RepayOverdueEvent())
                .bind(repaying, repaid, new RepaySuccessEvent())
                .bind(repaying, prepaid, new PrepayAllEvent())

                .bind(overdue, overduePaid, new RepayOverdueEvent())
                .bind(overdue, paidByCollection, new ConfirmCollectionEvent())
                .bind(overdue, closeAsLoss, new MarkLossEvent())

                .bind(repaid, completed, new CompleteEvent())
                .bind(prepaid, completed, new CompleteEvent())
                ;

        return machine;
    }

}
