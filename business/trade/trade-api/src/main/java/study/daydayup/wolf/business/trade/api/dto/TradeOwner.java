package study.daydayup.wolf.business.trade.api.dto;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.exception.InvalidTradeIdException;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm
 *
 * @author Wingle
 * @since 2020/1/14 11:06 上午
 **/
@Data
public class TradeOwner implements Request {
    protected Long buyerId;
    protected Long sellerId;

    public void valid() {
        if (buyerId == null && sellerId == null) {
            throw new InvalidTradeIdException("BuyerId and SellerId can't both null");
        }
    }
}
