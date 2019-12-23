package study.daydayup.wolf.business.trade.api.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.entity
 *
 * @author Wingle
 * @since 2019/12/16 4:08 下午
 **/
@Data
@SuperBuilder(toBuilder = true)
public class Trade extends Model {
    public Trade() {}

    protected String tradeNo;
    /**
     * @see TradeTypeEnum
     */
    protected int tradeType;
    protected int state;
    protected String relatedTradeNo;

    protected long buyerId;
    protected String buyerName;
    protected long sellerId;
    protected String sellerName;

    protected String source;
    protected String tags;

    protected LocalDateTime createdAt;
}
