package study.daydayup.wolf.demo.my.starter.ddd.trade;

import study.daydayup.wolf.demo.my.starter.ddd.trade.dal.OrderDAO;
import study.daydayup.wolf.demo.my.starter.ddd.trade.dal.OrderDO;
import study.daydayup.wolf.demo.my.starter.ddd.trade.model.Order;
import study.daydayup.wolf.framework.layer.domain.Event;
import study.daydayup.wolf.framework.layer.domain.Repository;

import java.util.Set;

/**
 * study.daydayup.wolf.demo.my.starter.ddd.trade
 *
 * @author Wingle
 * @since 2019/12/25 4:15 下午
 **/
public class OrderRepository implements Repository {
    public OrderEntity find(String orderNo) {
        OrderEntity entity = new OrderEntity(false);
        entity.setOrderNo(orderNo);

        return entity;
    }

    public String add(OrderEntity entity) {
        Order order = entity.getModel();
        OrderDO orderDO = new OrderDO(order);
        OrderDAO orderDAO = new OrderDAO();

        return orderDAO.insert(orderDO);
    }

    public void save(OrderEntity entity) {
        Order locker = entity.getKey();
        Order changes = entity.getChanges();

        OrderDO lockerDO = new OrderDO(locker);
        OrderDO changesDO = new OrderDO(changes);

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.update(lockerDO, changesDO);


        fireEvents(entity);
    }

    private void fireEvents(OrderEntity entity) {
        Set<Event> events = entity.getEventSet();

        for(Event event: events) {
            System.out.println("Event fired:" + event.getClass().getSimpleName());
        }

    }

    public void remove(String orderNo) {
        System.out.println("order removed: " + orderNo);
    }
}
