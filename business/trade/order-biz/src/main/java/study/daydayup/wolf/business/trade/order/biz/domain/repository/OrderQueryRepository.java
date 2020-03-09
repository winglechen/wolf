package study.daydayup.wolf.business.trade.order.biz.domain.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.TradeOwner;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.business.trade.order.biz.dal.dataobject.OrderDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.domain.repository
 *
 * @author Wingle
 * @since 2020/1/17 9:47 下午
 **/
@Component
public class OrderQueryRepository extends OrderRepository {
    protected List<Order> findExtraByOrderList(List<OrderDO> orderDOList, @NonNull TradeOwner owner) {
        if (CollectionUtil.isEmpty(orderDOList)) {
            return ListUtil.empty();
        }

        List<Order> orderList = converter.toModel(orderDOList);
        TradeIds tradeIds = initTradeIds(orderList, owner);
        if (tradeIds == null) {
            return orderList;
        }

        findAddressByOrderList(orderList, tradeIds);
        findLineByOrderList(orderList, tradeIds);

        return orderList;
    }

    private TradeIds initTradeIds(@NonNull List<Order> orderList, TradeOwner owner) {
        List<String> tradeNos = CollectionUtil.keys(orderList, Order::getTradeNo);
        if (!ListUtil.notEmpty(tradeNos)) {
            return null;
        }

        TradeIds tradeIds = new TradeIds();
        tradeIds.setBuyerId(owner.getBuyerId());
        tradeIds.setSellerId(owner.getSellerId());
        tradeIds.addAll(tradeNos);

        return tradeIds;
    }

    private void findLineByOrderList(List<Order> orderList, TradeIds tradeIds) {
        return;
    }

    private void findAddressByOrderList(List<Order> orderList, TradeIds tradeIds) {
        return;
    }

}
