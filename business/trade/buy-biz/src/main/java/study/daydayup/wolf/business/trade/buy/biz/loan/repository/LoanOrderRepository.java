package study.daydayup.wolf.business.trade.buy.biz.loan.repository;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.RelatedTradeRequest;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.domain.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.api.service.order.OrderService;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanOrderEntity;
import study.daydayup.wolf.common.util.lang.EnumUtil;
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

    public void save(LoanOrderEntity entity) {
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

    private Order findExistsOrder(LoanOrderEntity entity) {
        RelatedTradeRequest request = entityToRequest(entity);
        List<Order> orderList = orderService.findRelatedTrade(request).getData();
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

    private Order findRepayOrder(List<Order> orderList, LoanOrderEntity entity) {
        String entityTags = entity.getModel().getTags();

        for (Order order: orderList) {
            if (!entityTags.equals(order.getTags())) {
                continue;
            }
            return orderToKey(order);
        }

        return null;
    }

    private Order findLoanProxyOrder(List<Order> orderList, LoanOrderEntity entity) {
        return null;
    }

    private Order findCollectionOrder(List<Order> orderList, LoanOrderEntity entity) {
        return null;
    }

    private void updateEntity(LoanOrderEntity entity) {
        Order key = entity.getKey();
        Order changes = entityToChanges(entity);

        if (key == null || null == changes) {
            return;
        }

        orderService.modify(key, changes);
    }

    private void createEntity(LoanOrderEntity entity) {
        orderService.create(entity.getModel());
    }

    private RelatedTradeRequest entityToRequest(LoanOrderEntity entity) {
        RelatedTradeRequest request = new RelatedTradeRequest();

        BeanUtils.copyProperties(entity.getModel(), request);
        request.setExpiredAfter(LocalDateTime.now());

        return request;
    }

    private Order orderToKey(Order order) {
        if (order == null) {
            return null;
        }

        return Order.builder()
                .tradeNo(order.getTradeNo())
                .state(order.getState())
                .buyerId(order.getBuyerId())
                .sellerId(order.getSellerId())
                .version(order.getVersion())
                .build();
    }

    private Order entityToChanges(LoanOrderEntity entity) {
        if (entity == null || null == entity.getModel()) {
            return null;
        }

        Order order = entity.getModel();

        return Order.builder()
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
    }

}
