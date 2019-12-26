package study.daydayup.wolf.business.trade.order.biz.api;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;
import study.daydayup.wolf.business.trade.order.biz.domain.repository.ContractRepository;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.order.service.impl
 *
 * @author Wingle
 * @since 2019/12/16 10:32 上午
 **/
@Component
public class ContractServiceImpl implements ContractService {
    @Resource
    private ContractRepository contractRepository;

    @Override
    public void create(@Validated Contract contract) {
        contractRepository.add(contract);
    }

    @Override
    public void modify(@Validated Contract locker, Contract changes) {
        contractRepository.save(locker, changes);
    }

    @Override
    public Contract find(@Validated TradeId tradeId) {
        tradeId.valid();
        return contractRepository.find(tradeId);
    }
}
