package study.daydayup.wolf.business.trade.api.dto.buy.base.response;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.framework.layer.api.Response;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base.response
 *
 * @author Wingle
 * @since 2019/10/9 1:39 下午
 **/
@Data
public class BuyResponse implements Response {
    private String tradeNo;
    private Contract contract;
    private Order order;
}
