package study.daydayup.wolf.business.trade.order.biz.domain.entity;

import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.RepaymentTerm;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.LoanedState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.repay.DueState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.repay.OverdueState;
import study.daydayup.wolf.business.trade.api.domain.util.StateUtil;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.time.LocalDate;

/**
 * study.daydayup.wolf.business.trade.order.domain.entity
 *
 * @author Wingle
 * @since 2019/10/7 11:54 下午
 **/
public class ContractEntity extends AbstractEntity<Contract> implements Entity  {
    private ContractOption option;

    public ContractEntity(@NonNull Contract contract, ContractOption option) {
        this.model = contract;

        if (option != null) {
            this.option = option;
        } else {
            this.option = new ContractOption();
        }
    }

    public void init() {
        formatLoanContract();
    }

    private void formatLoanContract() {
        int loanTradeType = TradeTypeEnum.LOAN_CONTRACT.getCode();
        if (!BeanUtil.equals(loanTradeType, model.getTradeType())) {
            return;
        }

        if (!StateUtil.equals(model.getState(), new LoanedState())) {
            return;
        }

        initRepaymentTerm();
        formatInstallmentTerm();
    }

    private void initRepaymentTerm() {
        LoanTerm loan = model.getLoanTerm();
        if (null == loan) {
            return;
        }

        if (null != model.getRepaymentTerm()) {
            return;
        }

        RepaymentTerm term = RepaymentTerm.builder()
                .tradeNo(loan.getTradeNo())
                .buyerId(loan.getBuyerId())
                .sellerId(loan.getSellerId())
                .loanAmount(loan.getAmount())
                .currency(loan.getCurrency())
                .repayStrategy(loan.getRepayStrategy())
                .prepayStrategy(loan.getPrepayStrategy())
                .interestRate(loan.getInterestRate())
                .penaltyRate(loan.getPenaltyRate())
                .build();
        model.setRepaymentTerm(term);
    }

    private void formatInstallmentTerm() {
        LoanTerm loan = model.getLoanTerm();
        RepaymentTerm repayment = model.getRepaymentTerm();
        if (null == loan || null == repayment) {
            return;
        }

        initToday();
        for (InstallmentTerm installmentTerm : model.getInstallmentTermList()) {
            parseInstallment(loan, repayment, installmentTerm);
        }
    }

    private LocalDate today;
    private void initToday() {
        if (today != null) {
            return;
        }

        today = LocalDate.now();
    }

    private void  parseInstallment(LoanTerm loan, RepaymentTerm repayment, InstallmentTerm installment) {
        TradeState state = installment.getState();
        if (!StateUtil.equals(state, new DueState())
                && !StateUtil.equals(state, new OverdueState())) {
            return;
        }


    }


}
