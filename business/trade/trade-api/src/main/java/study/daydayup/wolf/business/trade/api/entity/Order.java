package study.daydayup.wolf.business.trade.api.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.entity.order.OrderLine;
import study.daydayup.wolf.business.trade.api.vo.BuyerMemo;
import study.daydayup.wolf.business.trade.api.vo.SellerMemo;
import study.daydayup.wolf.business.trade.api.vo.TradeAddress;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.entity
 *
 * @author Wingle
 * @since 2019/10/4 12:04 AM
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder(toBuilder = true)
public class Order extends Trade {
    protected Long  amount;
    protected Long  postage;
    protected Integer currency;

    protected String outTradeNo;

    protected Integer consignMethod;
    protected Integer paymentMethod;

    protected TradeAddress address;

    protected BuyerMemo buyerMemo;
    protected SellerMemo sellerMemo;

    protected List<OrderLine> orderLineList;
}
