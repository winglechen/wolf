package study.daydayup.wolf.business.trade.buy.domain.entity.flow;

import study.daydayup.wolf.business.trade.buy.domain.entity.node.TradeFlowNode;

import java.util.ArrayList;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 2:03 PM
 **/
public class AbstractTradeFlow implements TradeFlow {
    protected ArrayList<TradeFlowNode> nodeList;

    public void init() {
        nodeList = new ArrayList<>();
    }

    @Override
    public void addNode(TradeFlowNode node){
        nodeList.add(node);
    }
}
