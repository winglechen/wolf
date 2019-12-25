package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.vo.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.vo.contract.RepaymentTerm;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.entity
 *
 * @author Wingle
 * @since 2019/12/23 10:16 上午
 **/
@Data
public class LoanEntity extends AbstractEntity<Contract> implements Entity  {
    public LoanEntity(Contract model) {
        this.model = model;
        this.changes = new Contract();
        this.locker = Contract.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();
    }

    public void approve() {
        //loan.state:approved
        //loan.installment.effect
    }
    public void refuse() {
        //loan.state:refused
    }

    public void startLoan() {
        //fire loan order create event
    }
    public void completeLoan() {
        // pay notify -> order state:paid -> loan order paid
        // loan.service subscribe(loan order paid)
        // Loan.finishLoan
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
