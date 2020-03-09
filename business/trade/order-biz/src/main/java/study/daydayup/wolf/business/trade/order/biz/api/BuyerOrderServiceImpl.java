package study.daydayup.wolf.business.trade.order.biz.api;

import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.service.order.BuyerOrderService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.trade.order.biz.api
 *
 * @author Wingle
 * @since 2020/1/14 12:41 下午
 **/
@RpcService(protocol = "dubbo")
public class BuyerOrderServiceImpl implements BuyerOrderService {


    @Override
    public Result<Page<Order>> findAll(Long buyerId, PageRequest pageRequest) {
        return null;
    }
}
