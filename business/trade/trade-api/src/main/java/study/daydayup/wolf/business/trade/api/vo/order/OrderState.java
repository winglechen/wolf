package study.daydayup.wolf.business.trade.api.vo.order;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.framework.layer.api.Model;

/**
 * study.daydayup.wolf.business.trade.api.entity.buy
 *
 * @author Wingle
 * @since 2019/10/9 11:40 上午
 **/
@Data
public class OrderState extends Model {
    /**
     * @see study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum
     */
    private int tradeType;
    private String orderNo;
    private TradeState state;
}
