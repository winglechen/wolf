package study.daydayup.wolf.business.trade.order.biz.api;

import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.RelatedTradeRequest;
import study.daydayup.wolf.business.trade.api.service.order.SellerOrderService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.api
 *
 * @author Wingle
 * @since 2020/1/14 12:42 下午
 **/
@RpcService(protocol = "dubbo")
public class SellerOrderServiceImpl implements SellerOrderService {
    @Override
    public Result<Page<Order>> findAll(Long buyerId) {
        return null;
    }

    @Override
    public Result<List<Order>> findRelatedTrade(RelatedTradeRequest request) {
        return null;
    }


}
