package study.daydayup.wolf.business.trade.order.biz.api;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.order.OrderOption;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.RelatedTradeRequest;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.OrderRepository;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.service.impl
 *
 * @author Wingle
 * @since 2019/12/16 10:33 上午
 **/
@RpcService(protocol = "dubbo")
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;

    @Override
    public Result<Object> create(@Validated Order order) {
        orderRepository.add(order);
        return Result.ok();
    }

    @Override
    public Result<Object> modify(@Validated Order key, Order changes) {
        orderRepository.save(key, changes);
        return Result.ok();
    }

    @Override
    public Result<Order> find(@Validated TradeId tradeId) {
        return find(tradeId, null);
    }

    @Override
    public Result<Order> find(@Validated TradeId tradeId, OrderOption option) {
        tradeId.valid();
        Order order = orderRepository.find(tradeId, option);

        return Result.ok(order);
    }

    @Override
    public Result<List<Order>> findRelatedTrade(@Validated RelatedTradeRequest request) {
        request.valid();
        List<Order> orders = orderRepository.findRelatedTrade(request);

        return Result.ok(orders);
    }
}
