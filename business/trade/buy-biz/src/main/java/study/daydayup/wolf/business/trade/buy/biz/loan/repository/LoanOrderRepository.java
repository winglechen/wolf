package study.daydayup.wolf.business.trade.buy.biz.loan.repository;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.tm.RelatedTradeRequest;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.OrderEntity;
import study.daydayup.wolf.common.util.EnumUtil;
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
public class LoanOrderRepository extends AbstractRepository implements Repository {
    @Reference
    private OrderService orderService;

    public void save(OrderEntity entity) {
        if (entity == null || null == entity.getModel()) {
            return;
        }

        if (null != findExistsOrder(entity)) {
            entity.setKey(findExistsOrder(entity));
            updateEntity(entity);
            return;
        }

        createEntity(entity);
    }

    private Order findExistsOrder(OrderEntity entity) {
        RelatedTradeRequest request = entityToRequest(entity);
        List<Order> orderList = orderService.findRelatedTrade(request);
        if (orderList == null || orderList.isEmpty()) {
            return null;
        }

        TradeTypeEnum tradeType = EnumUtil.codeOf(entity.getModel().getTradeType(), TradeTypeEnum.class);
        switch (tradeType) {
            case LOAN_ORDER:
                return orderToKey(orderList.get(0));
            case OVERDUE_REPAY:
            case PREPAY_All:
            case PREPAY_ORDER:
            case REPAY_ORDER:
                return findRepayOrder(orderList, entity);
            case LOAN_PROXY:
                return findLoanProxyOrder(orderList, entity);
            case COLLECTION_ORDER:
                return findCollectionOrder(orderList, entity);
            default:
                return null;
        }
    }

    private Order findRepayOrder(List<Order> orderList, OrderEntity entity) {
        String entityTags = entity.getModel().getTags();

        for (Order order: orderList) {
            if (!entityTags.equals(order.getTags())) {
                continue;
            }
            return orderToKey(order);
        }

        return null;
    }

    private Order findLoanProxyOrder(List<Order> orderList, OrderEntity entity) {
        return null;
    }

    private Order findCollectionOrder(List<Order> orderList, OrderEntity entity) {
        return null;
    }

    private void updateEntity(OrderEntity entity) {
        Order key = entity.getKey();
        Order changes = entityToChanges(entity);

        if (key == null || null == changes) {
            return;
        }

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

    private Order orderToKey(Order order) {
        if (order == null) {
            return null;
        }

        Order key = Order.builder()
                .tradeNo(order.getTradeNo())
                .state(order.getState())
                .buyerId(order.getBuyerId())
                .sellerId(order.getSellerId())
                .version(order.getVersion())
                .build();

        return key;
    }

    private Order entityToChanges(OrderEntity entity) {
        if (entity == null || null == entity.getModel()) {
            return null;
        }

        Order order = entity.getModel();
        Order changes = Order.builder()
                .tags(order.getTags())
                .state(order.getState())
                .amount(order.getAmount())
                .postage(order.getPostage())
                .paymentMethod(order.getPaymentMethod())
                .consignMethod(order.getConsignMethod())
                .outTradeNo(order.getOutTradeNo())
                .expiredAt(order.getExpiredAt())
                .deleteFlag(order.getDeleteFlag())
                .build();

        return changes;
    }

}
