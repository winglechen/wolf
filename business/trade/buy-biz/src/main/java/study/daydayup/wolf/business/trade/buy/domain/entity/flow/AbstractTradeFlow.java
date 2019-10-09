package study.daydayup.wolf.business.trade.buy.domain.entity.flow;

import study.daydayup.wolf.business.trade.api.dto.buy.request.ConfirmRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PrepareRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PrepareResponse;
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
    public ConfirmResponse confirm(ConfirmRequest request) {
        ConfirmResponse response = new ConfirmResponse();

        for(TradeFlowNode node : nodeList) {
            node.run(request, response, context);
        }

        return response;
    }


    @Override
    public PrepareResponse preview(PrepareRequest request) {
        return null;
    }

    @Override
    public PayResponse pay(PayRequest request) {
        return null;
    }

    @Override
    public PayNotifyResponse payNotify(PayNotifyRequest request) {
        return null;
    }


}
