package study.daydayup.wolf.business.trade.api.service.order;

import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.FulltextRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.TypeRequest;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.trade.api.service.tm
 *
 * @author Wingle
 * @since 2019/10/9 6:56 下午
 **/
public interface SellerContractService {
    Result<Contract> findByTradeNo(TradeId tradeId);

    Result<Page<Contract>> findAll(Long sellerId, PageRequest pageRequest);
    Result<Page<Contract>> findByTradeType(TypeRequest request, PageRequest pageRequest);
    Result<Page<Contract>> findByTradeState(StateRequest request, PageRequest pageRequest);
    Result<Page<Contract>> findByBuyerId(BuyerRequest request, PageRequest pageRequest);

    Result<Page<Contract>> search(FulltextRequest request, PageRequest pageRequest);
}
