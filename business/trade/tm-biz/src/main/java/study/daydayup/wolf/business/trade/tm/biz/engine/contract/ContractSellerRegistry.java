package study.daydayup.wolf.business.trade.tm.biz.engine.contract;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.tm.biz.engine.FilterRegistry;
import study.daydayup.wolf.business.trade.tm.biz.engine.contract.seller.SellerContractByBuyerId;
import study.daydayup.wolf.business.trade.tm.biz.engine.contract.seller.SellerContractByBuyerName;
import study.daydayup.wolf.business.trade.tm.biz.engine.contract.seller.SellerContractByState;
import study.daydayup.wolf.business.trade.tm.biz.engine.contract.seller.SellertContractByTradeType;
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
    private ContractByTradeNo contractByTradeNo;
    @Resource
    private SellerContractByBuyerId sellerContractByBuyerId;
    @Resource
    private SellerContractByBuyerName sellerContractByBuyerName;
    @Resource
    private SellertContractByTradeType sellertContractByTradeType;
    @Resource
    private SellerContractByState sellerContractByState;

    @Override
    public void register() {
        this.addFilter(contractByTradeNo, sellerContractByBuyerId, sellertContractByTradeType, sellerContractByState, sellerContractByBuyerName);
    }
}
