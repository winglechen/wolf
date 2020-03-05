package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import study.daydayup.wolf.business.trade.api.config.TradeTag;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.LoanTerm;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.exception.order.InvalidContractException;
import study.daydayup.wolf.business.trade.api.domain.exception.buy.InstallmentNotEffectedException;
import study.daydayup.wolf.business.trade.api.domain.state.base.WaitToPayState;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.RelatedTradeRequest;
import study.daydayup.wolf.common.lang.enums.PeriodStrategyEnum;
import study.daydayup.wolf.common.lang.enums.finance.FeeStrategyEnum;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.id.TradeNo;
import study.daydayup.wolf.common.model.type.string.Tag;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
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

    public LoanOrderEntity(Order order) {
        this.model = order;
        this.isNew = false;
    }

    public void loan() {
        LocalDateTime expiredAt = LocalDateTime.now().plusDays(30);

        model = createOrder(TradeTypeEnum.LOAN_ORDER);
        model.setAmount(getLoanAmount());
        model.setExpiredAt(expiredAt);

        setLoanTag();
    }


    public void loanByProxy() {

    }

    public void repay() {

        TradeTypeEnum tradeType = TradeTypeEnum.REPAY_ORDER;
        model = createOrder(tradeType);

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

    //TODO CHECK
    private void setInstallmentTag(int installmentNo) {
        Tag orderTag = new Tag(TradeTag.INSTALLMENT_PREFIX + installmentNo);

        Tag contractTag = new Tag(model.getTags());
        if (contractTag.contains(TradeTag.FIRST_TRADE)
                && 1 == installmentNo) {
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
}
