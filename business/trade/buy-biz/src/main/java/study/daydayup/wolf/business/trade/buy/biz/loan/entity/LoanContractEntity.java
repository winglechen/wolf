package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.event.loan.ApproveEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.RefuseEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.loan.LoanBeginEvent;
import study.daydayup.wolf.business.trade.api.domain.event.loan.loan.LoanSuccessEvent;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

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
        LoanSuccessEvent event = LoanSuccessEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();

        key.setState(model.getState());
        changes.setStateEvent(event);

        //loan.installment.effect
        List<InstallmentTerm> installmentTerms = new ArrayList<>();
    }

    /**
     * JUST FOR DEV
     * @param effectAt
     */
    public void completeLoan(LocalDate effectAt) {
        // pay notify -> order state:paid -> loan order paid
        // loan.service subscribe(loan order paid)
        // Loan.finishLoan
        LoanSuccessEvent event = LoanSuccessEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();

        key.setState(model.getState());
        changes.setStateEvent(event);

        //loan.installment.effect
        List<InstallmentTerm> installmentTerms = new ArrayList<>();
    }

    public void due() {
        // loan.service.scan: due loan  -> fire loan due event
        // order.service.subscribe(loan due event)
        // order.create()
    }
    public void repay() {
        // order.pay -> order.state:paid -> fire order paid event
        // loan.service.subscribe(order paid event)
        // loan.state:change...
    }

    public void overdue() {

    }

    public void markAsLoss() {}


}
