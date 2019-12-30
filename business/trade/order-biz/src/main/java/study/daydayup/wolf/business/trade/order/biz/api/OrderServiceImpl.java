package study.daydayup.wolf.business.trade.order.biz.api;

import org.springframework.validation.annotation.Validated;
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
    public void create(@Validated Order order) {
        orderRepository.add(order);
    }

    @Override
    public void modify(@Validated Order key, Order changes) {
        orderRepository.save(key, changes);
    }

    @Override
    public Order find(@Validated TradeId tradeId) {
        tradeId.valid();
        return orderRepository.find(tradeId);
    }
}
