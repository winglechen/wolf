package study.daydayup.wolf.business.trade.order.biz.domain.entity;

import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.config.TradeTag;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.RepaymentTerm;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.exception.order.InvalidContractException;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.contract.LoanedState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.repay.DueState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.repay.EffectedState;
import study.daydayup.wolf.business.trade.api.domain.state.loan.repay.OverdueState;
import study.daydayup.wolf.business.trade.api.domain.util.StateUtil;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.common.lang.enums.PeriodStrategyEnum;
import study.daydayup.wolf.common.model.type.string.Tag;
import study.daydayup.wolf.common.util.finance.Interest;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.common.util.lang.EnumUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.common.util.time.PeriodUtil;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * study.daydayup.wolf.business.trade.order.domain.entity
 *
 * @author Wingle
 * @since 2019/10/7 11:54 下午
 **/
public class ContractEntity extends AbstractEntity<Contract> implements Entity  {
    private ContractOption option;

    public ContractEntity(@NonNull Contract contract) {
        this(contract, null);
    }

    public ContractEntity(@NonNull Contract contract, ContractOption option) {
        this.model = contract;

        if (option != null) {
            this.option = option;
        } else {
            this.option = new ContractOption();
        }
    }

    public void init() {
        initToday();
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

        if (null == model.getLoanTerm()) {
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
                .installmentNum(loan.getInstallmentNum())
                .amount(BigDecimal.ZERO)
                .loanAmount(BigDecimal.ZERO)
                .handlingFee(BigDecimal.ZERO)
                .interest(BigDecimal.ZERO)
                .penalty(BigDecimal.ZERO)
                .currency(loan.getCurrency())
                .repayStrategy(loan.getRepayStrategy())
                .prepayStrategy(loan.getPrepayStrategy())
                .build();
        model.setRepaymentTerm(term);
    }

    private void formatInstallmentTerm() {
        LoanTerm loan = model.getLoanTerm();
        RepaymentTerm repayment = model.getRepaymentTerm();
        initTags(repayment);

        for (InstallmentTerm installmentTerm : model.getInstallmentTermList()) {
            TradeState state = installmentTerm.getState();
            if (!StateUtil.inArray(state, new EffectedState(), new DueState(), new OverdueState())) {
                break;
            }

            parseInstallment(loan, repayment, installmentTerm);

            if (StateUtil.equals(state, new EffectedState())) {
                break;
            }
        }

        setRepaymentTags(repayment);
    }

    private LocalDate today;
    private void initToday() {
        if (today != null) {
            return;
        }

        today = LocalDate.now();
    }

    private Tag tags;
    private void initTags(RepaymentTerm repayment) {
        if (tags != null) {
            return;
        }

        tags = new Tag(repayment.getTags());
    }

    private void  parseInstallment(@NonNull LoanTerm loan, @NonNull RepaymentTerm repayment, @NonNull InstallmentTerm installment) {
        calculateInstallmentLoanAmount(repayment, installment);
        calculateInstallmentHandlingFee(repayment, installment);

        calculateInstallmentOverdueDays(loan, installment);
        calculateInstallmentInterest(loan, repayment, installment);
        calculateInstallmentPenalty(loan, repayment, installment);
        calculateInstallmentAmount(repayment, installment);

        setRepaymentDueAt(repayment, installment);
        addRepaymentTags(installment);
    }

    private void setRepaymentDueAt(@NonNull RepaymentTerm repayment, @NonNull InstallmentTerm installment) {
        if (null != repayment.getDueAt() || null == installment.getDueAt()) {
            return;
        }

        repayment.setDueAt(installment.getDueAt());
    }

    private void calculateInstallmentLoanAmount(@NonNull RepaymentTerm repayment, @NonNull InstallmentTerm installment) {
        BigDecimal amount = DecimalUtil.add(
                repayment.getLoanAmount(), installment.getLoanAmount()
        );

        repayment.setLoanAmount(amount);
    }

    private void calculateInstallmentHandlingFee(@NonNull RepaymentTerm repayment, @NonNull InstallmentTerm installment) {
        BigDecimal fee = DecimalUtil.add(
                repayment.getHandlingFee(), installment.getHandlingFee()
        );

        repayment.setHandlingFee(fee);
    }


    private void calculateInstallmentOverdueDays(@NonNull LoanTerm loan, @NonNull InstallmentTerm installment) {
        LocalDate effectAt = installment.getEffectAt();
        LocalDate dueAt = installment.getDueAt();
        if (null == effectAt || null == dueAt) {
            throw new InvalidContractException("installment effectAt and dueAt can't be null");
        }

        PeriodStrategyEnum strategy = EnumUtil.codeOf(loan.getPeriodStrategy(), PeriodStrategyEnum.class);
        int effectDays = PeriodUtil.daysBetween(effectAt, today, strategy);
        int overdueDays = effectDays - installment.getPeriod();

        installment.setEffectDays(effectDays);
        installment.setOverdueDays(overdueDays);
    }

    private void calculateInstallmentInterest(@NonNull LoanTerm loan, @NonNull RepaymentTerm repayment, @NonNull InstallmentTerm installment) {
        int period = Math.min(installment.getPeriod(), installment.getEffectDays());

        BigDecimal interest = Interest.rate(loan.getAmount(), loan.getInterestRate(), period);
        installment.setInterest(interest);

        BigDecimal repaymentInterest = DecimalUtil.add(
                repayment.getInterest(), interest
        );
        repayment.setInterest(repaymentInterest);
    }

    private void calculateInstallmentPenalty(@NonNull LoanTerm loan, @NonNull RepaymentTerm repayment, @NonNull InstallmentTerm installment) {
        int overdueDays = installment.getOverdueDays();
        if (overdueDays <= 0) {
            installment.setPenalty(BigDecimal.ZERO);
            return;
        }

        BigDecimal penalty = Interest.rate(installment.getLoanAmount(), loan.getPenaltyRate(), overdueDays);
        installment.setPenalty(penalty);

        BigDecimal repaymentPenalty = DecimalUtil.add(
                repayment.getPenalty(), penalty
        );
        repayment.setPenalty(repaymentPenalty);
    }

    private void calculateInstallmentAmount(@NonNull RepaymentTerm repayment, @NonNull InstallmentTerm installment) {
        BigDecimal amount = DecimalUtil.add(
                installment.getLoanAmount(),
                installment.getHandlingFee(),
                installment.getInterest(),
                installment.getPenalty()
        );
        installment.setAmount(amount);

        BigDecimal repaymentAmount = DecimalUtil.add(
                repayment.getAmount(), amount
        );
        repayment.setAmount(repaymentAmount);
    }

    private void addRepaymentTags(@NonNull InstallmentTerm installment) {
        Integer installmentNo = installment.getInstallmentNo();
        if (installmentNo == null) {
            throw new InvalidContractException("installmentNo can't be null");
        }

        String tag = StringUtil.join(TradeTag.INSTALLMENT_PREFIX, installmentNo);
        tags.add(tag);
    }

    private void setRepaymentTags(@NonNull RepaymentTerm repayment) {
        repayment.setTags(tags.toString());
    }

}
