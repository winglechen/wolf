package study.daydayup.wolf.business.trade.api.entity.order;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.entity.Trade;
import study.daydayup.wolf.business.trade.api.vo.order.OrderState;

/**
 * study.daydayup.wolf.business.trade.api.entity
 *
 * @author Wingle
 * @since 2019/10/4 12:04 AM
 **/
@Data
public class BaseOrder extends Trade {

    protected int amount;
    protected int postage;
    protected int paymentMethod;

    protected String payNo;
    protected String outerTradeNo;
}
