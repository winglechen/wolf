package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.vo.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.vo.contract.RepaymentTerm;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.entity
 *
 * @author Wingle
 * @since 2019/12/23 10:16 上午
 **/
public class LoanEntity extends Entity {
    private Contract model;
    private Contract changes;
    private Contract locker;

    public LoanEntity(Contract model) {
        this.model = model;
        this.changes = new Contract();
        this.locker = Contract.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();
    }

    public Contract getContract() {
        return model;
    }

    public RepaymentTerm getRepayment() {
        RepaymentTerm repayment = new RepaymentTerm();

        return repayment;
    }

    public List<InstallmentTerm> getInstallmentList() {

        return null;
    }
}
