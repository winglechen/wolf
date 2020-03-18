package study.daydayup.wolf.business.trade.api.service.order;

import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.*;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.TypeRequest;
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
public interface SellerContractService {
    Result<List<Contract>> findByTradeNos(TradeIds tradeIds);

    Result<Page<Contract>> findAll(Long sellerId, PageRequest pageRequest);
    Result<Page<Contract>> findByTradeType(TypeRequest request, PageRequest pageRequest);
    Result<Page<Contract>> findByTradeState(StateRequest request, PageRequest pageRequest);
    Result<Page<Contract>> findByBuyerId(BuyerRequest request, PageRequest pageRequest);
    Result<Page<Contract>> search(FulltextRequest request, PageRequest pageRequest);

    Result<Page<Contract>> findByInstallmentState(InstallmentStateRequest request, PageRequest pageRequest);
    Result<Page<Contract>> findOverdueContractByBuyer(InstallmentStateRequest request, PageRequest pageRequest);
    Result<Page<InstallmentTerm>> findDueInstallmentTerm(DueInstallmentRequest request, PageRequest pageRequest);
}
