package study.daydayup.wolf.business.trade.buy.biz.common.flow;

import study.daydayup.wolf.business.trade.api.dto.buy.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PreviewResponse;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeContext;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;

import java.util.ArrayList;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 2:03 PM
 **/
public abstract class AbstractTradeFlow implements TradeFlow {
    protected TradeContext context;
    protected ArrayList<TradeNode> nodeList;

    @Override
    public void init() {
        context = new TradeContext();
        nodeList = new ArrayList<>();
    }

    @Override
    public void addNode(TradeNode node){
        nodeList.add(node);
    }

    @Override
    public ConfirmResponse confirm(BuyRequest request) {
        ConfirmResponse response = new ConfirmResponse();

        for(TradeNode node : nodeList) {
            node.run(request, response, context);
        }

        return response;
    }


    @Override
    public PreviewResponse preview(BuyRequest request) {
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
