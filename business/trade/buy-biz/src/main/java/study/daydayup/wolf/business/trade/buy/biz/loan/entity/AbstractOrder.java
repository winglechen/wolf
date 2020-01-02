package study.daydayup.wolf.business.trade.buy.biz.loan.entity;

import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.common.model.type.id.TradeNo;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.buy.loan
 *
 * @author Wingle
 * @since 2019/12/13 4:41 下午
 **/
@Deprecated
public class AbstractOrder extends AbstractEntity<Order> implements Entity  {
    protected Contract contract;

    public AbstractOrder(Contract contract) {
        this.contract = contract;
        this.isNew = true;
    }

    protected Order createOrder(TradeTypeEnum tradeType) {
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
                .postage(0L)
                .currency(contract.getLoanTerm().getCurrency())
                .build();
    }

    protected String createOrderNo() {
        return TradeNo.builder()
                .accountId(contract.getBuyerId())
                .tradePhase(TradePhaseEnum.ORDER_PHASE)
                .build()
                .toString();
    }
}
