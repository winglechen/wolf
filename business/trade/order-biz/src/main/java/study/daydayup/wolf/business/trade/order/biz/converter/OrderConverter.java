package study.daydayup.wolf.business.trade.order.biz.converter;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderDO;
import study.daydayup.wolf.business.trade.order.biz.tsm.Tsm;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.converter.Converter;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.converter
 *
 * @author Wingle
 * @since 2020/3/9 4:25 下午
 **/
public class OrderConverter implements Converter {
    public OrderDO toDO(Order order) {
        if (order == null) {
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(order, orderDO);

        TradeState state = order.getState();
        if (state != null) {
            orderDO.setState(state.getCode());
        }

        return orderDO;
    }

    public Order toModel(OrderDO orderDO) {
        if (orderDO == null) {
            return null;
        }

        Order order = new Order();
        BeanUtils.copyProperties(orderDO, order);

        TradeState state = Tsm.getStateByCode(orderDO.getState(), orderDO.getTradeType());
        order.setState(state);

        return order;
    }

    public List<Order> toModel(List<OrderDO> orderDOs) {
        return CollectionUtil.to(orderDOs, this::toModel);
    }
}
