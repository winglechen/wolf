package study.daydayup.wolf.business.trade.api.domain.state;


import study.daydayup.wolf.common.sm.State;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.api.domain.state
 *
 * @author Wingle
 * @since 2019/10/5 11:22 PM
 **/
public interface TradeState extends State {
    int getCode();
    String getName();
    LocalDateTime getUpdatedAt();
}
