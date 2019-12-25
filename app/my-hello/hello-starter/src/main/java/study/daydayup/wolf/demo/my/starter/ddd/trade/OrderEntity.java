package study.daydayup.wolf.demo.my.starter.ddd.trade;

import lombok.Data;
import study.daydayup.wolf.demo.my.starter.ddd.trade.model.Order;
import study.daydayup.wolf.demo.my.starter.ddd.trade.model.OrderAddress;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;
import study.daydayup.wolf.framework.layer.domain.Event;

import java.util.HashSet;
import java.util.Set;

/**
 * study.daydayup.wolf.demo.my.starter.ddd.trade
 *
 * @author Wingle
 * @since 2019/12/25 4:15 下午
 **/
@Data
public class OrderEntity extends AbstractEntity implements Entity {
    private boolean isNew = false;

    private Set<Event> eventSet;

    private Order model;
    private Order changes;
    private Order locker;

    public OrderEntity() {
        this(true);
    }

    public OrderEntity(boolean isNew) {
        this.isNew = isNew;
        eventSet = new HashSet<>();
    }

    public void setOrderNo(String orderNo) {
        model.setOrderNo(orderNo);
        if (isNew) {
            return;
        }

        locker.setOrderNo(orderNo);
    }

    public void setBuyerId(long buyerId) {
        model.setBuyerId(buyerId);
        if (isNew) {
            return;
        }

        locker.setBuyerId(buyerId);
    }

    public void setAddress(OrderAddress address) {
        model.setAddress(address);
        if (isNew) {
            return;
        }
        changes.setAddress(address);
    }

    public void setAmount(long amount) {
        model.setAmount(amount);
        if (isNew) {
            return;
        }

        changes.setAmount(amount);
        fire(new PriceChangeEvent());
    }

    public void setState(int state) {
        if (!isNew) {
            locker.setState(model.getState());
            changes.setState(state);
        }

        model.setState(state);
    }

    public void fire(Event event) {
        eventSet.add(event);
    }

}
