package study.daydayup.wolf.business.trade.api.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.entity.trade.TradeStateLog;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.framework.layer.api.Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.entity
 *
 * @author Wingle
 * @since 2019/12/16 4:08 下午
 **/
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Trade implements Model {
    @NotBlank
    protected String tradeNo;
    /**
     * @see TradeTypeEnum
     */
    @NotNull @Min(1)
    protected Integer tradeType;
    protected TradeState state;
    protected TradeEvent stateEvent;
    protected String relatedTradeNo;

    protected Long  buyerId;
    protected String buyerName;
    protected Long  sellerId;
    protected String sellerName;

    protected String source;
    protected String tags;

    protected LocalDateTime createdAt;
    protected LocalDateTime expiredAt;

    protected TradeStateLog stateLog;
}
