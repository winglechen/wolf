package study.daydayup.wolf.business.trade.order.biz.api;

import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.Order;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.OrderRepository;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.order.service.impl
 *
 * @author Wingle
 * @since 2019/12/16 10:33 上午
 **/
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderRepository orderRepository;

    @Override
    public void create(Order order) {
        orderRepository.add(order);
    }

    @Override
    public void modify(Order locker, Order changes) {
        orderRepository.save(locker, changes);
    }

    @Override
    public Order find(TradeId tradeId) {
        tradeId.valid();
        return orderRepository.find(tradeId);
    }
}
