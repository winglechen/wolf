package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.RepaymentTerm;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.repay.RepayEffectEvent;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.ApprovedState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.LoaningState;
import study.daydayup.wolf.business.trade.api.domain.util.StateUtil;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.event.loan.ApproveEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.RefuseEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.loan.LoanBeginEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.loan.LoanSuccessEvent;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.common.lang.enums.PeriodStrategyEnum;
import study.daydayup.wolf.common.util.lang.EnumUtil;
import study.daydayup.wolf.common.util.time.PeriodUtil;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.entity
 *
 * @author Wingle
 * @since 2019/12/23 10:16 上午
 **/
public class LoanContractEntity extends AbstractEntity<Contract> implements Entity  {
    public LoanContractEntity(Contract model) {
        this(model, true);
    }

    public LoanContractEntity(Contract model, boolean isNew) {
        this.model = model;
        this.key = Contract.builder()
                .tradeNo(model.getTradeNo())
                .tradeType(model.getTradeType())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();

        this.changes = new Contract();
        this.isNew = isNew;
    }

    public LoanContractEntity(TradeId tradeId) {
        tradeId.valid();
        Contract contract = Contract.builder()
                .tradeNo(tradeId.getTradeNo())
                .buyerId(tradeId.getBuyerId())
                .sellerId(tradeId.getSellerId())
                .build();

        model = contract;
        key = contract;
        changes = new Contract();
        isNew = false;
    }

    public void approve() {
        //loan.state:approved
        ApproveEvent event = ApproveEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();

        key.setState(model.getState());
        changes.setStateEvent(event);
    }

    public boolean isRepayable() {
        RepaymentTerm repayment = model.getRepaymentTerm();
        if (model == null || null == repayment) {
            return false;
        }

        if (repayment.getAmount().compareTo(BigDecimal.ZERO) < 1) {
            return false;
        }

        return true;
    }

    public boolean isLoanable() {
        LoanTerm loan = model.getLoanTerm();
        if (null == model || null == loan) {
            return false;
        }

        return StateUtil.inArray(model.getState(), new ApprovedState(), new LoaningState());
    }

    public void refuse() {
        //loan.state:refused
        RefuseEvent event = RefuseEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();

        key.setState(model.getState());
        changes.setStateEvent(event);
    }

    public void startLoan() {
        LoanBeginEvent event = LoanBeginEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .currency(model.getLoanTerm().getCurrency())
                .build();

        key.setState(model.getState());
        changes.setStateEvent(event);

        //fire loan order create event
        fire(event);
    }

    public void completeLoan() {
        // pay notify -> order state:paid -> loan order paid
        // loan.service subscribe(loan order paid)
        // Loan.finishLoan
        markContractLoaned();

        //loan.installment.effect
        activeInstallments(LocalDate.now());
    }

    /**
     * JUST FOR DEV
     * @param effectAt mocked EffectAt
     */
    public void completeLoan(LocalDate effectAt) {
        // pay notify -> order state:paid -> loan order paid
        // loan.service subscribe(loan order paid)
        // Loan.finishLoan
        markContractLoaned();

        //loan.installment.effect
        activeInstallments(effectAt);
    }

    public void due() {
        // loan.service.scan: due loan  -> fire loan due event
        // order.service.subscribe(loan due event)
        // order.create()
    }
    public void repay(Order order) {
        // order.pay -> order.state:paid -> fire order paid event
        // loan.service.subscribe(order paid event)
        // loan.state:change...
    }

    public void overdue() {

    }

    public void markAsLoss() {}

    private void markContractLoaned() {
        LoanSuccessEvent event = LoanSuccessEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();

        key.setState(model.getState());
        changes.setStateEvent(event);
    }

    private void activeInstallments(@NonNull LocalDate effectAt) {
        TradeEvent effectEvent = new RepayEffectEvent();
        PeriodStrategyEnum strategy = EnumUtil.codeOf( model.getLoanTerm().getPeriodStrategy(), PeriodStrategyEnum.class );

        List<InstallmentTerm> ks = new ArrayList<>();
        List<InstallmentTerm> cs = new ArrayList<>();
        LocalDate start;
        LocalDate end = PeriodUtil.daysAfter(-1, PeriodStrategyEnum.OPEN_CLOSE, effectAt);

        for (InstallmentTerm term: model.getInstallmentTermList() ) {
            start = PeriodUtil.daysAfter(1, PeriodStrategyEnum.OPEN_CLOSE, end);
            end   = PeriodUtil.daysAfter(term.getPeriod(), strategy, start);

            InstallmentTerm k = initKeyInstallment(term);
            InstallmentTerm c = getChangedInstallment(start, end, effectEvent);

            ks.add(k);
            cs.add(c);
        }

        key.setInstallmentTermList(ks);
        changes.setInstallmentTermList(cs);
    }

    private InstallmentTerm initKeyInstallment(InstallmentTerm term) {
        return InstallmentTerm.builder()
                .tradeNo(term.getTradeNo())
                .installmentNo(term.getInstallmentNo())
                .state(term.getState())
                .buyerId(term.getBuyerId())
                .sellerId(term.getSellerId())
                .build();
    }

    private InstallmentTerm getChangedInstallment(LocalDate effectAt, LocalDate dueAt, TradeEvent event) {
        InstallmentTerm c = new InstallmentTerm();
        c.setEffectAt(effectAt);
        c.setDueAt(dueAt);
        c.setStateEvent(event);

        return c;
    }

}
