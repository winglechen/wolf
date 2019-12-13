package study.daydayup.wolf.business.trade.api.vo;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.vo.state.TradeState;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.util.Date;

/**
 * study.daydayup.wolf.business.trade.api.entity.buy
 *
 * @author Wingle
 * @since 2019/10/9 11:40 上午
 **/
@Data
public class OrderState extends Entity {
    /**
     * @see study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum
     */
    private int tradeType;
    private String orderNo;
    private TradeState state;
}
