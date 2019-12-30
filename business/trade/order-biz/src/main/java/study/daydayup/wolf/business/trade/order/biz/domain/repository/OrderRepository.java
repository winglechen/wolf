package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.mockito.internal.matchers.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.OrderOption;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.Order;
import study.daydayup.wolf.business.trade.api.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.exception.order.TradeStateNotFoundException;
import study.daydayup.wolf.business.trade.api.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.OrderDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderDO;
import study.daydayup.wolf.business.trade.order.biz.tsm.Tsm;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class OrderRepository extends AbstractRepository implements Repository {
    @Resource
    private OrderLineRepository lineRepository;
    @Resource
    private OrderAddressRepository addressRepository;
    @Resource
    private OrderDAO orderDAO;

    public void add(@Validated Order order) {
        insertOrder(order);

        lineRepository.add(order.getOrderLineList());
        addressRepository.add(order.getAddress());
    }

    public void save(@Validated Order key, Order changes) {
        if (key == null || null == changes) {
            return;
        }
        updateOrder(key, changes);
        lineRepository.save(key.getOrderLineList(), changes.getOrderLineList());
    }

    public Order find(TradeId tradeId) {
        return find(tradeId, null);
    }

    public Order find(TradeId tradeId, OrderOption option) {
        return null;
    }

    private void insertOrder(Order order) {
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(order, orderDO);

        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(order.getTradeType());
        TradeState state = stateMachine.getInitState();
        orderDO.setState(state.getCode());

        orderDAO.insertSelective(orderDO);
    }

    private int updateOrder(Order key, Order changes) {
        OrderDO keyDO = modelToDO(key);
        OrderDO changesDO = modelToDO(changes);
        changesDO.setUpdatedAt(LocalDateTime.now());

        TradeState state = Tsm.getStateByEvent(key.getTradeType(), key.getState(), changes.getStateEvent());
        if (state != null) {
            changesDO.setState(state.getCode());

            if (key.getState() != null) {
                keyDO.setState(key.getState().getCode());
            }
        }
        return orderDAO.updateByKey(keyDO, changesDO);
    }

    private OrderDO modelToDO(Order order) {
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

    private Order DOToModel(OrderDO orderDO) {
        if (orderDO == null) {
            return null;
        }

        Order order = new Order();
        BeanUtils.copyProperties(orderDO, order);

        TradeState state = Tsm.getStateByCode(orderDO.getState(), orderDO.getTradeType());
        order.setState(state);

        return order;
    }

}
