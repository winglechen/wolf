package study.daydayup.wolf.business.trade.api.dto.buy;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.trade.api.dto.buy
 *
 * @author Wingle
 * @since 2019/12/16 5:34 下午
 **/
@Data
public class OrderOption extends Request {
    private boolean withOrderLine;
    private boolean withAddress;

    private int orderLineNum    = -1;

    private boolean withRelatedOrders;
    private boolean withStateLog;
}
