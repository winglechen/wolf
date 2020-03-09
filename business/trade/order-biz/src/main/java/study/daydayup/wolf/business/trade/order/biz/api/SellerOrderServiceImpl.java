package study.daydayup.wolf.business.trade.order.biz.api;

import lombok.NonNull;
import org.mockito.internal.matchers.Or;
import study.daydayup.wolf.business.trade.api.domain.entity.Order;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.FulltextRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.TypeRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.RelatedTradeRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.business.trade.api.service.order.SellerOrderService;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.seller.SellerOrderRepository;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.order.biz.api
 *
 * @author Wingle
 * @since 2020/1/14 12:42 下午
 **/
@RpcService(protocol = "dubbo")
public class SellerOrderServiceImpl implements SellerOrderService {
    @Resource
    private SellerOrderRepository repository;

    @Override
    public Result<List<Order>> findByTradeNos(TradeIds tradeIds) {
        tradeIds.valid();
        List<Order> orderList = repository.findByTradeNos(tradeIds);
        return Result.ok(orderList);
    }

    @Override
    public Result<List<Order>> findByRelatedTradeNo(@NonNull String tradeNo, @NonNull Long sellerId) {
        List<Order> orderList = repository.findByRelatedTradeNo(tradeNo, sellerId);
        return Result.ok(orderList) ;
    }

    @Override
    public Result<Page<Order>> findAll(Long sellerId, PageRequest pageRequest) {
        Page<Order> orderList = repository.findAll(sellerId, pageRequest);
        return Result.ok(orderList) ;
    }

    @Override
    public Result<Page<Order>> findByTradeType(TypeRequest request, PageRequest pageRequest) {
        Page<Order> orderList = repository.findByTradeType(request, pageRequest);
        return Result.ok(orderList) ;
    }

    @Override
    public Result<Page<Order>> findByTradeState(StateRequest request, PageRequest pageRequest) {
        Page<Order> orderList = repository.findByTradeState(request, pageRequest);
        return Result.ok(orderList) ;
    }

    @Override
    public Result<Page<Order>> findByBuyerId(BuyerRequest request, PageRequest pageRequest) {
        Page<Order> orderList = repository.findByBuyerId(request, pageRequest);
        return Result.ok(orderList) ;
    }

    @Override
    public Result<Page<Order>> search(FulltextRequest request, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Result<List<Order>> findRelatedTrade(RelatedTradeRequest request) {
        List<Order> orderList = repository.findRelatedTrade(request);
        return Result.ok(orderList) ;
    }
}
