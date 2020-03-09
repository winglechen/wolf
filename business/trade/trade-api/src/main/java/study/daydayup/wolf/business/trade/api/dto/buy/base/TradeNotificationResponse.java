package study.daydayup.wolf.business.trade.api.dto.buy.base;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Response;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base
 *
 * @author Wingle
 * @since 2020/3/9 11:20 上午
 **/
@Data
@Builder
public class TradeNotificationResponse implements Response {
    private boolean success;
    private int cause = 0;
}
