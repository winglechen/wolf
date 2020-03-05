package study.daydayup.wolf.business.trade.api.dto.tm.trade;

import lombok.*;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.exception.InvalidTradeIdException;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.framework.layer.api.Request;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.dto.tm
 *
 * @author Wingle
 * @since 2019/12/30 11:10 下午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelatedTradeRequest extends TradeOwner implements Request {
    @NotBlank
    private String relatedTradeNo;

    private TradeTypeEnum tradeType;
    private TradeState state;
    private TradeEvent stateEvent;
    private LocalDateTime expiredAfter;
    private String hasTags;


    public void valid() {
        if (relatedTradeNo == null || relatedTradeNo.length() < 20) {
            throw new IllegalArgumentException("invalid tradeNo:" + relatedTradeNo);
        }

        super.valid();
    }
}
