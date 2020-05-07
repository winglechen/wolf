package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.order.OrderOption;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.RelatedTradeRequest;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.event.TradeEvent;
import study.daydayup.wolf.business.trade.api.domain.event.base.CreateEvent;
import study.daydayup.wolf.business.trade.api.domain.state.TradeState;
import study.daydayup.wolf.business.trade.order.biz.converter.OrderConverter;
import study.daydayup.wolf.business.trade.order.biz.dal.dao.OrderDAO;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderDO;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.order.OrderAddressRepository;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.order.OrderLineRepository;
import study.daydayup.wolf.business.trade.order.biz.tsm.Tsm;
import study.daydayup.wolf.common.sm.StateMachine;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2019/12/26 9:06 下午
 **/
@Component
public class OrderRepository extends AbstractRepository implements Repository {
    @Resource
    protected OrderLineRepository lineRepository;
    @Resource
    protected OrderAddressRepository addressRepository;
    @Resource
    protected TradeStateLogRepository stateLogRepository;
    @Resource
    protected OrderDAO orderDAO;

    protected OrderConverter converter = new OrderConverter();

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
        stateLogRepository.log(changes);
        lineRepository.save(key.getOrderLineList(), changes.getOrderLineList());
    }



    public Order find(TradeId tradeId) {
        return find(tradeId, null);
    }

    public Order find(TradeId tradeId, OrderOption option) {
        tradeId.valid();
        OrderDO orderDO = orderDAO.selectByTradeNo(tradeId.getTradeNo(), tradeId.getBuyerId(), tradeId.getSellerId());

        return findExtraByOrder(orderDO, tradeId);
    }

    protected Order findExtraByOrder(OrderDO orderDO, TradeId tradeId) {
        if (orderDO == null) {
            return null;
        }

        Order order = converter.toModel(orderDO);
        order.setOrderLineList(lineRepository.find(tradeId));
        order.setAddress(addressRepository.find(tradeId));
        return order;
    }

    protected void insertOrder(Order order) {
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(order, orderDO);

        StateMachine<TradeState, TradeEvent> stateMachine = Tsm.create(order.getTradeType());
        TradeState state = stateMachine.getInitState();
        orderDO.setState(state.getCode());

        orderDAO.insertSelective(orderDO);
    }

    protected int updateOrder(Order key, Order changes) {
        OrderDO keyDO = converter.toDO(key);
        OrderDO changesDO = converter.toDO(changes);
        changesDO.setUpdatedAt(LocalDateTime.now());

        TradeState state = Tsm.getStateByEvent(key.getTradeType(), key.getState(), changes.getStateEvent());
        if (state != null) {
            changesDO.setState(state.getCode());

            if (key.getState() != null) {
                changes.setState(key.getState());
                keyDO.setState(key.getState().getCode());
            }
        }
        return orderDAO.updateByKey(keyDO, changesDO);
    }


}
