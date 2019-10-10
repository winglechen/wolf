package study.daydayup.wolf.business.trade.api.vo.state;

import lombok.Data;

/**
 * study.daydayup.wolf.business.trade.api.vo.state
 *
 * @author Wingle
 * @since 2019/10/5 11:23 PM
 **/
@Data
public class ClosedState extends AbstractTradeState implements TradeState {
    private String name = "交易关闭";
}
