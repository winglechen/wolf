package study.daydayup.wolf.business.trade.order.biz.api;

import lombok.NonNull;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.service.order.BuyerContractService;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.buyer.BuyerContractRepository;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.order.biz.api
 *
 * @author Wingle
 * @since 2020/1/14 12:40 下午
 **/
@RpcService(protocol = "dubbo")
public class BuyerContractServiceImpl implements BuyerContractService {
    @Resource
    private BuyerContractRepository repository;

    @Override
    public Result<Page<Contract>> findAll(@NonNull Long buyerId, PageRequest pageRequest) {
        Page<Contract> contracts = repository.findAll(buyerId, pageRequest);

        return Result.ok(contracts);
    }

    @Override
    public Result<Contract> findLatest(@NonNull Long buyerId) {
        Contract contract = repository.findLatest(buyerId);

        return Result.ok(contract);
    }
}
