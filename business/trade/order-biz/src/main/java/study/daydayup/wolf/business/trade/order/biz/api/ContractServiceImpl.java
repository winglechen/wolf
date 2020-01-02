package study.daydayup.wolf.business.trade.order.biz.api;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.order.ContractOption;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.ContractRepository;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.order.service.impl
 *
 * @author Wingle
 * @since 2019/12/16 10:32 上午
 **/
@RpcService(protocol = "dubbo")
public class ContractServiceImpl implements ContractService {
    @Resource
    private ContractRepository contractRepository;

    @Override
    public void create(@Validated Contract contract) {
        contractRepository.add(contract);
    }

    @Override
    public void modify(@Validated Contract key, Contract changes) {
        contractRepository.save(key, changes);
    }

    @Override
    public Contract find(@Validated TradeId tradeId) {
        return find(tradeId, null);
    }

    @Override
    public Contract find(TradeId tradeId, ContractOption option) {
        tradeId.valid();
        return contractRepository.find(tradeId);
    }
}
