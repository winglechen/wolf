package study.daydayup.wolf.business.trade.tm.biz.engine;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.tm.OrderRequest;
import study.daydayup.wolf.business.trade.tm.biz.engine.core.AbstractEngine;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;

/**
 * study.daydayup.wolf.business.trade.tm.biz.engine
 *
 * @author Wingle
 * @since 2020/1/10 12:26 下午
 **/
@Component
public class OrderQueryEngine extends AbstractEngine<OrderRequest> {
    public Result<Page<Order>> query(OrderRequest request) {
        init(request);

        return null;
    }

}
