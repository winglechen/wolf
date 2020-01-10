package study.daydayup.wolf.business.trade.api.service.tm;

import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.tm.OrderRequest;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;

/**
 * study.daydayup.wolf.business.trade.api.service.tm
 *
 * @author Wingle
 * @since 2019/10/9 9:35 下午
 **/
public interface OrderManageService extends Service {
    Result<Page<Order>> find(OrderRequest request);
}
