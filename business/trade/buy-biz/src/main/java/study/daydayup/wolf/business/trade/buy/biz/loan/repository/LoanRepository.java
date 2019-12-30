package study.daydayup.wolf.business.trade.buy.biz.loan.repository;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.TradeId;
import study.daydayup.wolf.business.trade.api.entity.Contract;
import study.daydayup.wolf.business.trade.api.service.order.ContractService;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanEntity;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.repository
 *
 * @author Wingle
 * @since 2019/12/26 8:36 下午
 **/
@Component
public class LoanRepository extends AbstractRepository implements Repository {
    @Resource
    private ContractService contractService;

    public void add(LoanEntity entity) {
        if (entity == null || null == entity.getModel()) {
            return;
        }

        contractService.create(entity.getModel());
        fire(entity.getEventList());
    }

    public void save(LoanEntity entity) {
        if (entity == null
                || null == entity.getKey()
                || null == entity.getChanges()) {
            return;
        }

        contractService.modify(entity.getKey(), entity.getChanges());
        fire(entity.getEventList());
    }

    public LoanEntity find(TradeId tradeId) {
        tradeId.valid();
        Contract contract = contractService.find(tradeId);

        if (contract == null) {
            return null;
        }

        LoanEntity entity = new LoanEntity(contract, false);
        return entity;
    }
}
