package study.daydayup.wolf.business.trade.tm.biz.engine.contract;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.tm.biz.engine.FilterRegistry;
import study.daydayup.wolf.business.trade.tm.biz.engine.contract.seller.ByBuyerId;
import study.daydayup.wolf.business.trade.tm.biz.engine.contract.seller.ByBuyerName;
import study.daydayup.wolf.business.trade.tm.biz.engine.contract.seller.ByState;
import study.daydayup.wolf.business.trade.tm.biz.engine.contract.seller.ByTradeType;
import study.daydayup.wolf.business.trade.tm.biz.engine.core.AbstractRegistry;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.tm.biz.engine.contract
 *
 * @author Wingle
 * @since 2020/1/10 3:16 下午
 **/
@Component
public class ContractSellerRegistry extends AbstractRegistry implements FilterRegistry {
    @Resource
    private ByTradeNo byTradeNo;
    @Resource
    private ByBuyerId byBuyerId;
    @Resource
    private ByBuyerName byBuyerName;
    @Resource
    private ByTradeType byTradeType;
    @Resource
    private ByState byState;

    @Override
    public void register() {
        this.addFilter(byTradeNo, byBuyerId, byTradeType, byState, byBuyerName);
    }
}
