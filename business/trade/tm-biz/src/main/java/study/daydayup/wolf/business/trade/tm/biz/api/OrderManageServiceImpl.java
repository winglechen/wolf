package study.daydayup.wolf.business.trade.tm.biz.api;

import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.tm.OrderRequest;
import study.daydayup.wolf.business.trade.api.service.tm.OrderManageService;
import study.daydayup.wolf.business.trade.tm.biz.engine.OrderQueryEngine;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.tm.biz.api
 *
 * @author Wingle
 * @since 2020/1/10 12:21 下午
 **/
@RpcService(protocol = "dubbo")
public class OrderManageServiceImpl implements OrderManageService {
    @Resource
    private OrderQueryEngine engine;
    @Override
    public Result<Page<Order>> find(OrderRequest request) {
        return engine.query(request);
    }
}
