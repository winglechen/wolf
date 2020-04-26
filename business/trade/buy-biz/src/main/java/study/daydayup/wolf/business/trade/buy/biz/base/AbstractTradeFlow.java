package study.daydayup.wolf.business.trade.buy.biz.base;

import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayResultRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PreviewResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResultResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PreviewResponse;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContextBuilder;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 2:03 PM
 **/
public abstract class AbstractTradeFlow implements TradeFlow {
    @Override
    public void init() {
    }

    @Override
    public PreviewResponse confirm(BuyRequest request) {
        BuyContext context = BuyContextBuilder.build(request);

        PreviewResponse response = new PreviewResponse();

        List<TradeNode> nodeList = buildConfirmFlow();
        execute(nodeList, context);

        if (null != context.getContract()) {
            response.setTradeNo(context.getContract().getTradeNo());
        } else if (null != context.getOrder()) {
            response.setTradeNo(context.getOrder().getTradeNo());
        }

        return response;
    }


    @Override
    public PreviewResponse preview(BuyRequest request) {
        BuyContext context = BuyContextBuilder.build(request);

        List<TradeNode> nodeList = buildPreviewFlow();
        execute(nodeList, context);

        PreviewResponse response = new PreviewResponse();
        response.setContract(context.getContract());
        return response;
    }

    @Override
    public PayResponse pay(PayRequest request) {
        return null;
    }

    @Override
    public PayResultResponse payNotify(PayResultRequest request) {
        return null;
    }

    protected void execute(List<TradeNode> nodeList, BuyContext context) {
        for(TradeNode node : nodeList) {
            node.run(context);
        }
    }


}
