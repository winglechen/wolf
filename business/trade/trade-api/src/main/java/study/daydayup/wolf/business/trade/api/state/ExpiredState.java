package study.daydayup.wolf.business.trade.api.state;

import lombok.Data;

/**
 * study.daydayup.wolf.business.trade.api.state
 *
 * @author Wingle
 * @since 2019/10/5 11:23 PM
 **/
@Data
public class ExpiredState extends AbstractTradeState implements TradeState {
    private String name = "支付超时";
}