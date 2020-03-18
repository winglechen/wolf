package study.daydayup.wolf.business.trade.order.biz.api;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.domain.entity.contract.InstallmentTerm;
import study.daydayup.wolf.business.trade.api.dto.tm.contract.seller.*;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.TradeIds;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.BuyerRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.StateRequest;
import study.daydayup.wolf.business.trade.api.dto.tm.trade.seller.TypeRequest;
import study.daydayup.wolf.business.trade.api.service.order.SellerContractService;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.contract.InstallmentTermRepository;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.seller.SellerContractRepository;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;
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
 * @since 2020/1/14 12:39 下午
 **/
@RpcService(protocol = "dubbo")
public class SellerContractServiceImpl implements SellerContractService {
    @Resource
    private SellerContractRepository repository;
    @Resource
    private InstallmentTermRepository installmentTermRepository;


    @Override
    public Result<List<Contract>> findByTradeNos(@NonNull TradeIds tradeIds) {
        tradeIds.valid();
        List<Contract> contractList = repository.findByTradeNos(tradeIds);
        return Result.ok(contractList);
    }

    @Override
    public Result<Page<Contract>> findAll(Long sellerId, PageRequest pageRequest) {
        Page<Contract> contracts = repository.findAll(sellerId, pageRequest);

        return Result.ok(contracts);
    }

    @Override
    public Result<Page<Contract>> findByTradeType(TypeRequest request, PageRequest pageRequest) {
        Page<Contract> contracts = repository.findByTradeType(request, pageRequest);
        return Result.ok(contracts);
    }

    @Override
    public Result<Page<Contract>> findByTradeState(StateRequest request, PageRequest pageRequest) {
        Page<Contract> contracts = repository.findByTradeState(request, pageRequest);
        return Result.ok(contracts);
    }

    @Override
    public Result<Page<Contract>> findByBuyerId(BuyerRequest request, PageRequest pageRequest) {
        Page<Contract> contracts = repository.findByBuyerId(request, pageRequest);
        return Result.ok(contracts);
    }

    @Override
    public Result<Page<Contract>> findByInstallmentState(InstallmentStateRequest request, PageRequest pageRequest) {
        Page<InstallmentTerm> installmentTermList = installmentTermRepository.findDueForSeller(
                request.getDueAt(), request.getSellerId(), pageRequest
        );

        TradeIds tradeIds = getTradeIdsByInstallmentList(installmentTermList.getData(), request.getSellerId());
        if (null == tradeIds) {
            Page<Contract>  contractPage = Page.empty(pageRequest.getPageNum(), pageRequest.getPageSize());
            return Result.ok(contractPage);
        }

        List<Contract> contractList = findByTradeNos(tradeIds).getData();
        Page<Contract> contractPage = installmentTermList.to(contractList);

        return Result.ok(contractPage);
    }

    @Override
    public Result<Page<Contract>> findOverdueContractByBuyer(@Validated InstallmentStateRequest request, PageRequest pageRequest) {
        if (null == request.getBuyerId()) {
            Page<Contract>  contractPage = Page.empty(pageRequest.getPageNum(), pageRequest.getPageSize());
            return Result.ok(contractPage);
        }

        Page<InstallmentTerm> installmentTermList = installmentTermRepository.findDueForBuyer(
                request.getDueAt(), request.getBuyerId(), request.getSellerId(), pageRequest
        );

        TradeIds tradeIds = getTradeIdsByInstallmentList(installmentTermList.getData(), request.getSellerId());
        if (null == tradeIds) {
            Page<Contract>  contractPage = Page.empty(pageRequest.getPageNum(), pageRequest.getPageSize());
            return Result.ok(contractPage);
        }

        List<Contract> contractList = findByTradeNos(tradeIds).getData();
        Page<Contract> contractPage = installmentTermList.to(contractList);

        return Result.ok(contractPage);
    }

    private TradeIds getTradeIdsByInstallmentList(List<InstallmentTerm> installmentTermList, Long sellerId) {
        if (!ListUtil.notEmpty(installmentTermList)) {
            return null;
        }

        List<String> tradeNos = CollectionUtil.keys(installmentTermList, InstallmentTerm::getTradeNo);
        if (!ListUtil.notEmpty(tradeNos)) {
            return null;
        }

        TradeIds tradeIds = new TradeIds();
        tradeIds.setSellerId(sellerId);
        tradeIds.addAll(tradeNos);

        return tradeIds;
    }

    @Override
    public Result<Page<InstallmentTerm>> findDueInstallmentTerm(@Validated DueInstallmentRequest request, PageRequest pageRequest) {
        Page<InstallmentTerm> installmentTermList = installmentTermRepository.findDueForSeller(
                request.getDueAt(), request.getSellerId(), pageRequest
        );
        return Result.ok(installmentTermList);
    }

    @Override
    public Result<Page<Contract>> search(FulltextRequest request, PageRequest pageRequest) {
        return null;
    }
}
