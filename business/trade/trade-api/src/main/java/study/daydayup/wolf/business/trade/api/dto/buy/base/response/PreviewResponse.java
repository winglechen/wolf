package study.daydayup.wolf.business.trade.api.dto.buy.base.response;

import lombok.Data;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.entity.Order;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy.base.response
 *
 * @author Wingle
 * @since 2019/10/9 1:39 下午
 **/
@Data
public class PreviewResponse {
    private Contract contract;
    private Order order;
}
