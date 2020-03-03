package study.daydayup.wolf.business.trade.api.dto.buy.base.response;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Response;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/10/5 11:27 AM
 **/
@Data
public class ConfirmResponse implements Response {
    private String tradeNo;
}
