package study.daydayup.wolf.business.trade.order.biz.api;

import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.FulltextRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.TypeRequest;
import study.daydayup.wolf.business.trade.api.service.order.SellerContractService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;

/**
 * study.daydayup.wolf.business.trade.order.biz.api
 *
 * @author Wingle
 * @since 2020/1/14 12:39 下午
 **/
@RpcService(protocol = "dubbo")
public class SellerContractServiceImpl implements SellerContractService {
    @Override
    public Result<Page<Contract>> find(Long sellerId) {
        return null;
    }

    @Override
    public Result<Page<Contract>> findByTradeNo(TradeId tradeId) {
        return null;
    }

    @Override
    public Result<Page<Contract>> findByTradeType(TypeRequest request) {
        return null;
    }

    @Override
    public Result<Page<Contract>> findByTradeState(StateRequest request) {
        return null;
    }

    @Override
    public Result<Page<Contract>> findByBuyerId(BuyerRequest request) {
        return null;
    }

    @Override
    public Result<Page<Contract>> search(FulltextRequest request) {
        return null;
    }
}
