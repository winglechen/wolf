package study.daydayup.wolf.business.trade.api.service.order;

import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.FulltextRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.TypeRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.RelatedTradeRequest;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.api.service.tm
 *
 * @author Wingle
 * @since 2019/10/9 6:56 下午
 **/
public interface SellerOrderService {
    Result<Page<Order>> findAll(Long sellerId, PageRequest pageRequest);
    Result<Page<Order>> findByTradeType(TypeRequest request, PageRequest pageRequest);
    Result<Page<Order>> findByTradeState(StateRequest request, PageRequest pageRequest);
    Result<Page<Order>> findByBuyerId(BuyerRequest request, PageRequest pageRequest);
    Result<Page<Order>> search(FulltextRequest request, PageRequest pageRequest);


    Result<List<Order>> findRelatedTrade(RelatedTradeRequest request);
}
