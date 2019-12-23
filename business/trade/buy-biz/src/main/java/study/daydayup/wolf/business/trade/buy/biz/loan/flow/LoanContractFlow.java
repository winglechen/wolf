package study.daydayup.wolf.business.trade.buy.biz.loan.flow;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.buy.biz.common.AbstractTradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.loan.node.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private LoanRequestValidationNode validationNode;
    @Resource
    private BuyerInfoCheckNode buyerInfoCheckNode;
    @Resource
    private BlockListCheckNode blockListCheckNode;
    @Resource
    private RefusedBlockListNode refusedBlockListNode;
    @Resource
    private OngoingLoanCheckNode ongoingLoanCheckNode;
    @Resource
    private FetchGoodsNode fetchGoodsNode;
    @Resource
    private GetSellerNode getSellerNode;
    @Resource
    private CreateContractNode createContractNode;
    @Resource
    private FirstLoanCheckNode firstLoanCheckNode;
    @Resource
    private FakeLoanEffectTimeNode fakeLoanEffectTimeNode;
    @Resource
    private StoreContractNode storeContractNode;

    @Override
    public List<TradeNode> buildPreviewFlow() {
        List<TradeNode> nodeList = new ArrayList<>();

        nodeList.add(validationNode);
        nodeList.add(buyerInfoCheckNode);
        nodeList.add(blockListCheckNode);
        nodeList.add(refusedBlockListNode);
        nodeList.add(ongoingLoanCheckNode);
        nodeList.add(fetchGoodsNode);
        nodeList.add(getSellerNode);
        nodeList.add(createContractNode);
        nodeList.add(firstLoanCheckNode);
        nodeList.add(fakeLoanEffectTimeNode);

        return nodeList;
    }

    @Override
    public List<TradeNode> buildConfirmFlow() {
        List<TradeNode> nodeList = buildPreviewFlow();

        nodeList.add(storeContractNode);

        return nodeList;
    }

    @Override
    public List<TradeNode> buildPayFlow() {
        List<TradeNode> nodeList = new ArrayList<>();

        return nodeList;
    }

    @Override
    public List<TradeNode> buildPayNotifyFlow() {
        List<TradeNode> nodeList = new ArrayList<>();

        return nodeList;
    }
}
