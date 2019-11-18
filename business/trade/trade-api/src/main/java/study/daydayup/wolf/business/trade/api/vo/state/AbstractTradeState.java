package study.daydayup.wolf.business.trade.api.vo.state;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.enums.state.DisputeStateEnum;

import java.util.Date;

/**
 * study.daydayup.wolf.business.trade.api.vo.state
 *
 * @author Wingle
 * @since 2019/10/10 11:26 上午
 **/
@Data
public abstract class AbstractTradeState {
    protected String TradeNo;
    protected int state;

    /**
     * @see study.daydayup.wolf.business.trade.api.enums.state.PaymentStateEnum
     */
    protected int paymentState;
    protected Date paidAt;

    /**
     * @see study.daydayup.wolf.business.trade.api.enums.state.DeliveryStateEnum
     */
    protected int deliveryState;
    protected Date sendedAt;

    /**
     * @see study.daydayup.wolf.business.trade.api.enums.state.CompletedStateEnum
     */
    protected int completedState;
    protected Date completedAt;

    /**
     * @see DisputeStateEnum
     */
    protected int AfterSaleState;
    protected Date afterSaleAt;

    protected Date createAt;
}
