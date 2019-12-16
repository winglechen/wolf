package study.daydayup.wolf.business.trade.api.state;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.enums.state.CompletedStateEnum;
import study.daydayup.wolf.business.trade.api.enums.state.ConsignStateEnum;
import study.daydayup.wolf.business.trade.api.enums.state.DisputeStateEnum;
import study.daydayup.wolf.business.trade.api.enums.state.PayStateEnum;

import java.time.LocalDateTime;


/**
 * study.daydayup.wolf.business.trade.api.state
 *
 * @author Wingle
 * @since 2019/10/10 11:26 上午
 **/
@Data
public abstract class AbstractTradeState {
    protected String TradeNo;
    protected int state;

    /**
     * @see PayStateEnum
     */
    protected int paymentState;
    protected LocalDateTime paidAt;

    /**
     * @see ConsignStateEnum
     */
    protected int consignState;
    protected LocalDateTime consignedAt;

    /**
     * @see CompletedStateEnum
     */
    protected int completedState;
    protected LocalDateTime completedAt;

    /**
     * @see DisputeStateEnum
     */
    protected int disputeState;
    protected LocalDateTime disputedAt;

    protected LocalDateTime createAt;
}
