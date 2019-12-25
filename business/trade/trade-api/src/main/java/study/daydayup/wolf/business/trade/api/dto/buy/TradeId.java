package study.daydayup.wolf.business.trade.api.dto.buy;

import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/12/25 9:33 下午
 **/
public class TradeId implements Request {
    private String tradeNo;

    private Long buyerId;
    private Long sellerId;
}
