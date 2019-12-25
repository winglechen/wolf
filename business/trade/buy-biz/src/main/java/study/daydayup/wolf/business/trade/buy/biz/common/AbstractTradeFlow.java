package study.daydayup.wolf.business.trade.buy.biz.common;

import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.BuyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.base.response.PreviewResponse;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContextBuilder;

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
    public ConfirmResponse confirm(BuyRequest request) {
        BuyContext context = BuyContextBuilder.build(request);

        ConfirmResponse response = new ConfirmResponse();

        List<TradeNode> nodeList = buildConfirmFlow();
        execute(nodeList, context);

        return response;
    }


    @Override
    public PreviewResponse preview(BuyRequest request) {
        BuyContext context = BuyContextBuilder.build(request);

        PreviewResponse response = new PreviewResponse();
        List<TradeNode> nodeList = buildPreviewFlow();
        execute(nodeList, context);

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

    protected void execute(List<TradeNode> nodeList, BuyContext context) {
        for(TradeNode node : nodeList) {
            node.run(context);
        }
    }


}
