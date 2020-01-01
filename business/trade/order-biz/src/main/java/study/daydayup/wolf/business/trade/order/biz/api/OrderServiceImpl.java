package study.daydayup.wolf.business.trade.order.biz.api;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.OrderOption;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.tm.RelatedTradeRequest;
import study.daydayup.wolf.business.trade.api.entity.Order;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.OrderRepository;
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
    public void create(@Validated Order order) {
        orderRepository.add(order);
    }

    @Override
    public void modify(@Validated Order key, Order changes) {
        orderRepository.save(key, changes);
    }

    @Override
    public Order find(@Validated TradeId tradeId) {
        return find(tradeId, null);
    }

    @Override
    public Order find(@Validated TradeId tradeId, OrderOption option) {
        tradeId.valid();
        return orderRepository.find(tradeId, option);
    }

    @Override
    public List<Order> findRelatedTrade(@Validated RelatedTradeRequest request) {
        request.valid();
        return orderRepository.findRelatedTrade(request);
    }
}
