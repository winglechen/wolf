package study.daydayup.wolf.business.trade.api.event;

import lombok.Data;
import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.exception.InvalidTradeEventException;

/**
 * study.daydayup.wolf.business.trade.api.event
 *
 * @author Wingle
 * @since 2019/10/5 11:14 PM
 **/
@Data
public abstract class AbstractTradeEvent implements TradeEvent{
    protected String tradeNo;
    protected String relatedTradeNo;
    /**
     * @see TradeTypeEnum
     */
    protected int tradeType;

    protected long buyerId;
    protected long sellerId;

    protected String source;
    protected String tags;

    @Override
    public long getBuyerId() {
        checkBuyerAndSeller();
        return buyerId;
    }

    @Override
    public long getSellerId() {
        checkBuyerAndSeller();
        return sellerId;
    }

    @Override
    public int getTradeType() {
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
