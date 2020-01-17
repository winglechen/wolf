package study.daydayup.wolf.business.trade.api.domain.event;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.exception.InvalidTradeEventException;

/**
 * study.daydayup.wolf.business.trade.api.domain.event
 *
 * @author Wingle
 * @since 2019/10/5 11:14 PM
 **/
@Data
@SuperBuilder(toBuilder = true)
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

    public Integer notNullTradeType() {
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
