package study.daydayup.wolf.business.trade.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/12/16 5:34 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderOption implements Request {
    private boolean withOrderLine       = true;
    private boolean withAddress         = true;

    private Integer orderLineNum        = -1;

    private boolean withRelatedOrders   = false;
    private boolean withStateLog        = false;
}
