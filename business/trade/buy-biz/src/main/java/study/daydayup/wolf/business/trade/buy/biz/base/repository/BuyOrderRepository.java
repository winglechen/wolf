package study.daydayup.wolf.business.trade.buy.biz.base.repository;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanOrderEntity;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.repository
 *
 * @author Wingle
 * @since 2019/12/26 8:36 下午
 **/
@Component
public class BuyOrderRepository extends AbstractRepository implements Repository {
    @Reference
    private OrderService orderService;

    public void save(LoanOrderEntity entity) {
        if (entity == null || null == entity.getModel()) {
            return;
        }

        if (entity.isNew()) {
            createEntity(entity);
            return;
        }

        updateEntity(entity);
    }

    private void updateEntity(LoanOrderEntity entity) {
        Order key = entity.getKey();
        Order changes = entity.getChanges();

        if (key == null || null == changes) {
            return;
        }

        orderService.modify(key, changes);
    }

    private void createEntity(LoanOrderEntity entity) {
        orderService.create(entity.getModel());
    }

}
