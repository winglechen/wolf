package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import study.daydayup.wolf.business.trade.api.config.TradeTag;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.exception.order.InvalidContractException;
import study.daydayup.wolf.business.trade.api.domain.exception.buy.InstallmentNotEffectedException;
import study.daydayup.wolf.common.lang.enums.PeriodStrategyEnum;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.id.TradeNo;
import study.daydayup.wolf.common.model.type.string.Tag;
import study.daydayup.wolf.common.util.lang.EnumUtil;
import study.daydayup.wolf.common.util.time.PeriodUtil;
import study.daydayup.wolf.common.util.finance.Interest;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * study.daydayup.wolf.business.trade.buy.loan
 *
 * @author Wingle
 * @since 2019/12/13 4:41 下午
 **/
public class LoanOrderEntity extends AbstractEntity<Order> implements Entity  {
    private Contract contract;

    public LoanOrderEntity(Contract contract) {
        this.contract = contract;
        this.isNew = true;
    }

    public void loan() {
        LocalDateTime expiredAt = LocalDateTime.now().plusDays(30);

        model = createOrder(TradeTypeEnum.LOAN_ORDER);
        model.setAmount(contract.getLoanTerm().getAmount());
        model.setExpiredAt(expiredAt);

        setLoanTag();
    }

    public void loanByProxy() {

    }

    public void repay(int installmentNo) {
        InstallmentTerm installmentTerm = getInstallmentTerm(installmentNo);

        TradeTypeEnum tradeType = getRepayType(installmentTerm);
        model = createOrder(tradeType);
        model.setExpiredAt(getExpiredAt());

        int period = calculatePeriod(installmentTerm);
        BigDecimal amount = calculateAmount(installmentTerm, period);
        model.setAmount(amount);

        setInstallmentTag(installmentNo);
    }

    public void prepayAll() {

    }

    public void collect(int installmentNo) {
        repay(installmentNo);
        model.setTradeType(TradeTypeEnum.COLLECTION_ORDER.getCode());
    }

    public void collectPartly() {}

    public void collectAll() {

    }

    private void setLoanTag() {
        Tag contractTag = new Tag(model.getTags());
        if (!contractTag.contains(TradeTag.FIRST_TRADE)) {
            return;
        }
        model.setTags(TradeTag.FIRST_TRADE);
    }

    private void setInstallmentTag(int installmentNo) {
        Tag orderTag = new Tag(TradeTag.INSTALLMENT_PREFIX + installmentNo);

        Tag contractTag = new Tag(model.getTags());
        if (contractTag.contains(TradeTag.FIRST_TRADE)
                && 1 == installmentNo) {
            orderTag.add(TradeTag.FIRST_TRADE);
        }

        model.setTags(orderTag.toString());
    }

    private InstallmentTerm getInstallmentTerm(int installmentNo) {
        InstallmentTerm installmentTerm = contract.getInstallmentTermList().get(installmentNo-1);
        if (installmentTerm == null) {
            throw new InvalidContractException("Can't find installment:" + installmentNo);
        }

        return installmentTerm;
    }

    private TradeTypeEnum getRepayType(InstallmentTerm term) {
        LocalDate today = LocalDate.now();
        LocalDate dueAt = term.getDueAt();

        if (dueAt.equals(today)) {
            return TradeTypeEnum.REPAY_ORDER;
        }

        if (dueAt.isBefore(today)) {
            return TradeTypeEnum.REPAY_ORDER;
        }

        return TradeTypeEnum.OVERDUE_REPAY;
    }

    private LocalDateTime getExpiredAt() {
        LocalDate today = LocalDate.now();
        LocalTime time = LocalTime.of(23, 59, 59);

        return LocalDateTime.of(today, time);
    }

    private int calculatePeriod(InstallmentTerm term) {
        LocalDate effectAt = term.getEffectAt();
        LocalDate today = LocalDate.now();
        if (effectAt == null || effectAt.isBefore(today)) {
            throw new InstallmentNotEffectedException();
        }

        Integer loanStrategy = contract.getLoanTerm().getPeriodStrategy();
        if (loanStrategy == null) {
            throw new InvalidContractException("Period Strategy is null");
        }

        PeriodStrategyEnum strategy = EnumUtil.codeOf(loanStrategy, PeriodStrategyEnum.class);
        return PeriodUtil.daysBetween(effectAt, today, strategy);
    }

    private BigDecimal calculateAmount(InstallmentTerm term, int period) {
        BigDecimal amount = term.getAmount();
        BigDecimal fee = term.getHandlingFee();
        BigDecimal interest = calculateInterest(term, period);
        BigDecimal penalty = calculatePenalty(term, period);

        return amount.add(fee).add(interest).add(penalty);
    }

    private BigDecimal calculateInterest(InstallmentTerm term, int period) {
        int termPeriod = term.getPeriod();
        int interestPeriod = Math.min(period, termPeriod);

        LoanTerm loan = contract.getLoanTerm();
        return Interest.rate(loan.getAmount(), loan.getInterestRate(), interestPeriod);
    }

    private BigDecimal calculatePenalty(InstallmentTerm term, int period) {
        int termPeriod = term.getPeriod();
        if (period <= termPeriod) {
            return BigDecimal.ZERO;
        }

        int penaltyPeriod = period - termPeriod;
        LoanTerm loan = contract.getLoanTerm();

        return Interest.rate(loan.getAmount(), loan.getPenaltyRate(), penaltyPeriod, true);
    }

    private Order createOrder(TradeTypeEnum tradeType) {
        return Order.builder()
                .tradeNo(createOrderNo())
                .tradeType(tradeType.getCode())
                .relatedTradeNo(contract.getTradeNo())
                .buyerId(contract.getBuyerId())
                .buyerName(contract.getBuyerName())
                .sellerId(contract.getSellerId())
                .sellerName(contract.getSellerName())
                .source(contract.getSource())
                .createdAt(LocalDateTime.now())
                .postage(BigDecimal.ZERO)
                .currency(contract.getLoanTerm().getCurrency())
                .build();
    }

    private String createOrderNo() {
        return TradeNo.builder()
                .accountId(contract.getBuyerId())
                .tradePhase(TradePhaseEnum.ORDER_PHASE)
                .build()
                .create();
    }
}
