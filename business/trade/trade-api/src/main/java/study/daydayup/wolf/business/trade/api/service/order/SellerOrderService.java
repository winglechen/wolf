package study.daydayup.wolf.business.trade.api.service.order;

import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.RelatedTradeRequest;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.service.tm
 *
 * @author Wingle
 * @since 2019/10/9 6:56 下午
 **/
public interface SellerOrderService {
    Result<Page<Order>> find(Long buyerId);

    Result<List<Order>> findRelatedTrade(RelatedTradeRequest request);
}
