package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.entity.Contract;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.base.node.AbstractTradeNode;
import study.daydayup.wolf.business.trade.buy.biz.loan.entity.LoanContractEntity;
import study.daydayup.wolf.business.trade.buy.biz.loan.repository.LoanContractRepository;

import javax.annotation.Resource;


/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.node
 *
 * @author Wingle
 * @since 2019/12/17 8:19 下午
 **/
@Component
public class StoreContractNode extends AbstractTradeNode implements TradeNode {
    @Resource
    private LoanContractRepository repository;

    @Override
    public void run(BuyContext context) {
        if (!context.getRequest().isPreview()) {
            return;
        }

        Contract contract = context.getContract();

        LoanContractEntity entity = new LoanContractEntity(contract);
        repository.add(entity);
    }

}
