package study.daydayup.wolf.business.trade.buy.biz.loan.flow;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.buy.biz.common.AbstractTradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.loan.node.CreateContractNode;
import study.daydayup.wolf.business.trade.buy.biz.loan.node.FetchGoodsNode;
import study.daydayup.wolf.business.trade.buy.biz.loan.node.GetSellerNode;
import study.daydayup.wolf.business.trade.buy.biz.loan.node.StoreContractNode;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.loan
 *
 * @author Wingle
 * @since 2019/12/13 4:42 下午
 **/
@Component
public class LoanContractFlow extends AbstractTradeFlow implements TradeFlow {
    @Resource
    private FetchGoodsNode fetchGoodsNode;
    @Resource
    private GetSellerNode getSellerNode;
    @Resource
    private CreateContractNode createContractNode;
    @Resource
    private StoreContractNode storeContractNode;


    @Override
    public List<TradeNode> buildConfirmFlow() {
        return null;
    }

    @Override
    public List<TradeNode> buildPreviewFlow() {
        return null;
    }

    @Override
    public List<TradeNode> buildPayFlow() {
        return null;
    }

    @Override
    public List<TradeNode> buildPayNotifyFlow() {
        return null;
    }
}
