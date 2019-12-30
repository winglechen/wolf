package study.daydayup.wolf.business.trade.api.service.order;

import study.daydayup.wolf.business.trade.api.dto.OrderOption;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.tm.RelatedTradeRequest;
import study.daydayup.wolf.business.trade.api.entity.Order;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.service.tm
 *
 * @author Wingle
 * @since 2019/10/9 6:56 下午
 **/
public interface OrderService {
    void create(Order order);
    void modify(Order key, Order changes);
    Order find(TradeId tradeId);
    Order find(TradeId tradeId, OrderOption option);
    List<Order> findRelatedTrade(RelatedTradeRequest request);
}
