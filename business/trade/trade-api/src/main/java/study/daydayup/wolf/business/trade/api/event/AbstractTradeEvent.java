package study.daydayup.wolf.business.trade.api.event;

import lombok.Setter;
import study.daydayup.wolf.business.trade.api.enums.TradePhaseEnum;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.exception.InvalidTradeEventException;

/**
 * study.daydayup.wolf.business.trade.api.event
 *
 * @author Wingle
 * @since 2019/10/5 11:14 PM
 **/
@Setter
public abstract class AbstractTradeEvent implements TradeEvent{
    protected String tradeNo;
    protected long buyerId;
    protected long sellerId;

    /**
     * @see TradeTypeEnum
     */
    protected int tradeType;
    /**
     * @see TradePhaseEnum
     */
    protected int tradePhase;

    @Override
    public String getTradeNo() {
        return tradeNo;
    }

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

    @Override
    public int getTradePhase() {
        if (tradePhase <= 0) {
            throw new InvalidTradeEventException("Can't find tradePhase");
        }
        return tradePhase;
    }

    protected void checkBuyerAndSeller() {
        if (0 >= buyerId && 0 >= sellerId) {
            throw new InvalidTradeEventException("Can't find buyerId or SellerId.");
        }
    }
}
