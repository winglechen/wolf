package study.daydayup.wolf.business.trade.buy.biz.common;

import study.daydayup.wolf.business.trade.api.dto.buy.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PreviewResponse;
import study.daydayup.wolf.business.trade.api.enums.TradePhaseEnum;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContextBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 2:03 PM
 **/
public abstract class AbstractTradeFlow implements TradeFlow {
    protected List<TradeNode> nodeList;

    @Override
    public void init() {
        nodeList = new ArrayList<>();
    }

    @Override
    public void addNode(TradeNode node){
        nodeList.add(node);
    }

    @Override
    public ConfirmResponse confirm(BuyRequest request) {
        BuyContext context = BuyContextBuilder.build(request);
        context.setTradePhase(TradePhaseEnum.CONFIRM_PHASE);

        ConfirmResponse response = new ConfirmResponse();
        execute(context);

        return response;
    }


    @Override
    public PreviewResponse preview(BuyRequest request) {
        BuyContext context = BuyContextBuilder.build(request);
        context.setTradePhase(TradePhaseEnum.PREVIEW_PHASE);

        PreviewResponse response = new PreviewResponse();
        execute(context);

        return response;
    }

    @Override
    public PayResponse pay(PayRequest request) {
        return null;
    }

    @Override
    public PayNotifyResponse payNotify(PayNotifyRequest request) {
        return null;
    }

    protected void execute(BuyContext context) {
        for(TradeNode node : nodeList) {
            node.run(context);
        }
    }


}
