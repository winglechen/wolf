package study.daydayup.wolf.business.trade.api.vo.state;

import java.util.Date;

/**
 * study.daydayup.wolf.business.trade.api.vo.state
 *
 * @author Wingle
 * @since 2019/10/5 11:22 PM
 **/
public interface TradeState {
    String getTradeNo();

    int getState();
    String getName();

    int getPaymentState();
    Date getPaidAt();

    int getDeliveryState();
    Date getSendedAt();

    int getCompletedState();
    Date getCompletedAt();

    int getAfterSaleState();
    Date getAfterSaleAt();
}
