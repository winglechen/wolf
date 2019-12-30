package study.daydayup.wolf.business.trade.api.dto.tm;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.exception.InvalidTradeIdException;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm
 *
 * @author Wingle
 * @since 2019/12/30 11:10 下午
 **/
@Data
public class RelatedTradeRequest implements Request {
    @NotBlank
    private String relatedTradeNo;

    private Long buyerId;
    private Long sellerId;

    private TradeTypeEnum tradeType;
    private TradeState state;
    private LocalDateTime expiredAfter;

    public void valid() {
        if (relatedTradeNo == null || relatedTradeNo.length() < 20) {
            throw new IllegalArgumentException("invalid tradeNo:" + relatedTradeNo);
        }

        if (buyerId == null && sellerId == null) {
            throw new InvalidTradeIdException("BuyerId and SellerId can't both null");
        }
    }
}
