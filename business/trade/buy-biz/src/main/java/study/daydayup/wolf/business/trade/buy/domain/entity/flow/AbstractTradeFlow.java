package study.daydayup.wolf.business.trade.buy.domain.entity.flow;

import study.daydayup.wolf.business.trade.api.dto.buy.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.BuyResponse;
import study.daydayup.wolf.business.trade.buy.domain.entity.context.TradeFlowContext;
import study.daydayup.wolf.business.trade.buy.domain.entity.node.TradeFlowNode;

import java.util.ArrayList;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 2:03 PM
 **/
public abstract class AbstractTradeFlow implements TradeFlow {
    protected TradeFlowContext context;
    protected ArrayList<TradeFlowNode> nodeList;

    @Override
    public void init() {
        context = new TradeFlowContext();
        nodeList = new ArrayList<>();
    }

    @Override
    public void addNode(TradeFlowNode node){
        nodeList.add(node);
    }

    @Override
    public BuyResponse handle(BuyRequest request) {
        BuyResponse response = new BuyResponse();

        for(TradeFlowNode node : nodeList) {
            node.run(request, response, context);
        }

        return response;
    }
}
