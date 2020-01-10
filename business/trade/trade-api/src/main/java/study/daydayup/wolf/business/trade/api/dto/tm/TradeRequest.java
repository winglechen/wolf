package study.daydayup.wolf.business.trade.api.dto.tm;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.domain.exception.InvalidTradeIdException;
import study.daydayup.wolf.framework.layer.api.Request;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm
 *
 * @author Wingle
 * @since 2020/1/9 7:42 下午
 **/
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class TradeRequest implements Request {
    protected String tradeNo;
    protected Long buyerId;
    protected Long sellerId;

    protected Integer tradeType;
    protected Integer state;
    protected String buyerName;

    protected String tags;
    protected String source;

    protected String createdBefore;
    protected String createdAfter;
    protected LocalDateTime createdStart;
    protected LocalDateTime createdEnd;

    protected Integer version;

    public void valid() {
        if (tradeNo == null || tradeNo.length() < 20) {
            throw new InvalidTradeIdException("invalid tradeNo:" + tradeNo);
        }

        if (buyerId == null && sellerId == null) {
            throw new InvalidTradeIdException("BuyerId and SellerId can't both null");
        }
    }
}
