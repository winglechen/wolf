package study.daydayup.wolf.business.trade.api.dto.buy.base.response;

import study.daydayup.wolf.framework.layer.api.Response;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base.response
 *
 * @author Wingle
 * @since 2019/10/9 2:08 下午
 **/
public class PayResultResponse implements Response {
    private String tradeNo;
    private Boolean success;
    private BigDecimal amount;
}
