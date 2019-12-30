package study.daydayup.wolf.business.trade.buy.biz.loan.repository;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.tm.RelatedTradeRequest;
import study.daydayup.wolf.business.trade.api.entity.Order;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.OrderEntity;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.repository
 *
 * @author Wingle
 * @since 2019/12/26 8:36 下午
 **/
@Component
public class OrderRepository extends AbstractRepository implements Repository {
    @Reference
    private OrderService orderService;

    public void save(OrderEntity entity) {
        if (entity == null || null == entity.getModel()) {
            return;
        }

        if (isExists(entity)) {
            updateEntity(entity);
            return;
        }

        createEntity(entity);
    }

    private boolean isExists(OrderEntity entity) {
        RelatedTradeRequest request = entityToRequest(entity);
        List<Order> orderList = orderService.findRelatedTrade(request);
        if (orderList == null || orderList.isEmpty()) {
            return false;
        }

        return true;
    }

    private void updateEntity(OrderEntity entity) {
        Order key = entityToKey(entity);
        Order changes = entityToChanges(entity);

        orderService.modify(key, changes);
    }

    private void createEntity(OrderEntity entity) {
        orderService.create(entity.getModel());
    }

    private RelatedTradeRequest entityToRequest(OrderEntity entity) {
        RelatedTradeRequest request = new RelatedTradeRequest();

        BeanUtils.copyProperties(entity.getModel(), request);
        request.setExpiredAfter(LocalDateTime.now());

        return request;
    }

    private Order entityToKey(OrderEntity entity) {
        return null;
    }

    private Order entityToChanges(OrderEntity entity) {
        return null;
    }

}
