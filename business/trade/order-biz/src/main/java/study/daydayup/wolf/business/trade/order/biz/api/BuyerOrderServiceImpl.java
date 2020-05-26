package study.daydayup.wolf.business.trade.order.biz.api;

import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.business.trade.api.dto.order.OrderOption;
import study.daydayup.wolf.business.trade.api.service.order.BuyerOrderService;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.buyer.BuyerOrderRepository;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.order.biz.api
 *
 * @author Wingle
 * @since 2020/1/14 12:41 下午
 **/
@RpcService
public class BuyerOrderServiceImpl implements BuyerOrderService {
    @Resource
    private BuyerOrderRepository repository;

    @Override
    public Result<Page<Order>> findAll(Long buyerId, PageRequest pageRequest) {
        Page<Order> orders = repository.findAll(buyerId, pageRequest);

        return Result.ok(orders);
    }

    @Override
    public Result<Order> findLatest(TradeOwner owner, OrderOption option) {
        Order order = repository.findLatest(owner.getBuyerId(), owner.getBuyerId());
        return Result.ok(order);
    }
}
