package study.daydayup.wolf.business.trade.api.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.domain.entity.order.OrderLine;
import study.daydayup.wolf.business.trade.api.domain.vo.BuyerMemo;
import study.daydayup.wolf.business.trade.api.domain.vo.SellerMemo;
import study.daydayup.wolf.business.trade.api.domain.vo.OrderAddress;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.domain.entity
 *
 * @author Wingle
 * @since 2019/10/4 12:04 AM
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Order extends Trade {
    @NotNull @Min(0)
    protected BigDecimal amount;
    protected BigDecimal  postage;
    protected Integer currency;

    protected String outTradeNo;

    protected Integer consignMethod;
    protected Integer paymentMethod;

    protected OrderAddress address;

    protected BuyerMemo buyerMemo;
    protected SellerMemo sellerMemo;

    protected List<OrderLine> orderLineList;
}
