package study.daydayup.wolf.business.trade.api.state;


import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.state
 *
 * @author Wingle
 * @since 2019/10/5 11:22 PM
 **/
public interface TradeState {
    int getCode();
    LocalDateTime getUpdatedAt();
    String getName();
}
