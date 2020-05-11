package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.config.TradeTag;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.RepaymentTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.trade.TradeStateLog;
import study.daydayup.wolf.business.trade.api.domain.enums.PaymentReturnEnum;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.event.base.PaidEvent;
import study.daydayup.wolf.business.trade.api.domain.state.base.PaidState;
import study.daydayup.wolf.business.trade.api.domain.state.base.WaitToPayState;
import study.daydayup.wolf.business.trade.api.domain.util.StateUtil;
import study.daydayup.wolf.business.trade.api.dto.buy.base.TradeNotification;
import study.daydayup.wolf.common.lang.enums.finance.FeeStrategyEnum;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.string.id.TradeNo;
import study.daydayup.wolf.common.model.type.string.Tag;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
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

    public LoanOrderEntity(Order order) {
        model = order;
        key = Order.builder()
                .tradeNo(model.getTradeNo())
                .tradeType(model.getTradeType())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();

        isNew = false;
    }

    private void initChanges() {
        if (changes != null) {
            return;
        }

        changes = new Order();
    }

    public int paid(@Validated TradeNotification notification) {
        if (StateUtil.equals(model.getState(), new PaidState())) {
            return PaymentReturnEnum.DUPLICATE.getCode();
        }

        if (!StateUtil.equals(model.getState(), new WaitToPayState())) {
            return PaymentReturnEnum.ERROR.getCode();
        }

        PaidEvent event = PaidEvent.builder()
                .tradeNo(model.getTradeNo())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())
                .build();

        key.setState(model.getState());

        model.setStateEvent(event);
        model.setOutTradeNo(notification.getOutTradeNo());
        model.setPaymentMethod(notification.getPaymentMethod());

        initChanges();
        changes.setStateEvent(event);
        changes.setOutTradeNo(notification.getOutTradeNo());
        changes.setPaymentMethod(notification.getPaymentMethod());

        logStateChange();
        return PaymentReturnEnum.SUCCESS.getCode();
    }


    public void loan() {
        if (contract == null) {
            return;
        }
        isNew = true;

        LocalDateTime expiredAt = LocalDateTime.now().plusDays(30);
        model = createOrder(TradeTypeEnum.LOAN_ORDER);
        model.setAmount(getLoanAmount());
        model.setExpiredAt(expiredAt);

        setLoanTag();
    }


    public void loanByProxy() {

    }

    public void repay() {
        RepaymentTerm repayment = contract.getRepaymentTerm();
        if (contract == null || null == repayment) {
            return;
        }
        isNew = true;

        TradeTypeEnum tradeType = TradeTypeEnum.REPAY_ORDER;
        model = createOrder(tradeType);
        model.setAmount(repayment.getAmount());

        LocalDateTime expireAt = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
        model.setExpiredAt(expireAt);

        setRepayTag();
    }

    public void prepayAll() {

    }

    public void collect() {
    }

    public void collectPartly() {}

    public void collectAll() {

    }

    private void setLoanTag() {
        Tag contractTag = new Tag(contract.getTags());
        if (!contractTag.contains(TradeTag.FIRST_TRADE)) {
            return;
        }
        model.setTags(TradeTag.FIRST_TRADE);
    }

    private void setRepayTag() {
        Tag contractTag = new Tag(contract.getTags());

        String repayTags = contract.getRepaymentTerm().getTags();
        Tag orderTag = new Tag(repayTags);

        String firstInstallment = StringUtil.join(TradeTag.INSTALLMENT_PREFIX, 1);
        if (contractTag.contains(TradeTag.FIRST_TRADE) && orderTag.contains(firstInstallment)) {
            orderTag.add(TradeTag.FIRST_TRADE);
        }

        model.setTags(orderTag.toString());
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

    private BigDecimal getLoanAmount() {
        LoanTerm loan = contract.getLoanTerm();
        if (FeeStrategyEnum.PRE.getCode() == loan.getFeePayStrategy()) {
            BigDecimal result = loan.getAmount();
            result = result.subtract(loan.getHandlingFee());
            return DecimalUtil.scale(result);
        }

        return loan.getAmount();
    }

    private void logStateChange() {
        TradeStateLog log = TradeStateLog.builder()
                .tradeNo(model.getTradeNo())
                .relatedTradeNo(model.getRelatedTradeNo())
                .tradeType(model.getTradeType())
                .buyerId(model.getBuyerId())
                .sellerId(model.getSellerId())

                .sourceState(model.getState().getCode())
                .amount(model.getAmount())
                .paymentMethod(model.getPaymentMethod())
                .consignMethod(model.getConsignMethod())
                .tags(model.getTags())
                .source(model.getSource())
                .sourceVersion(model.getVersion())
                .createdAt(LocalDateTime.now())
                .build();

        changes.setStateLog(log);
    }
}
