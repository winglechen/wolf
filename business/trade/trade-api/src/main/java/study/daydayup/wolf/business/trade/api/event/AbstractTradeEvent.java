package study.daydayup.wolf.business.trade.api.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.exception.InvalidTradeEventException;

/**
 * study.daydayup.wolf.business.trade.api.event
 *
 * @author Wingle
 * @since 2019/10/5 11:14 PM
 **/
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractTradeEvent implements TradeEvent{
    protected String tradeNo;
    protected String relatedTradeNo;
    /**
     * @see TradeTypeEnum
     */
    protected Integer tradeType;

    protected Long  buyerId;
    protected Long  sellerId;

    protected String source;
    protected String tags;

    @Override
    public Long  getBuyerId() {
        checkBuyerAndSeller();
        return buyerId;
    }

    @Override
    public Long  getSellerId() {
        checkBuyerAndSeller();
        return sellerId;
    }

    @Override
    public Integer getTradeType() {
        if (tradeType <= 0) {
            throw new InvalidTradeEventException("Can't find tradeType");
        }

        return tradeType;
    }

    protected void checkBuyerAndSeller() {
        if (0 >= buyerId && 0 >= sellerId) {
            throw new InvalidTradeEventException("Can't find buyerId or SellerId.");
        }
    }
}
