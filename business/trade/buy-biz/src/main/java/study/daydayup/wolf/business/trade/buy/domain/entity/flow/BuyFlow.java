package study.daydayup.wolf.business.trade.buy.domain.entity.flow;

import study.daydayup.wolf.business.trade.api.dto.buy.request.ConfirmRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayNotifyRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PayRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.request.PrepareRequest;
import study.daydayup.wolf.business.trade.api.dto.buy.response.ConfirmResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayNotifyResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PayResponse;
import study.daydayup.wolf.business.trade.api.dto.buy.response.PrepareResponse;
import study.daydayup.wolf.business.trade.api.enums.TradeTypeEnum;
import study.daydayup.wolf.business.trade.buy.domain.entity.node.*;
import study.daydayup.wolf.business.trade.buy.domain.factory.TradeFlowFactory;
import study.daydayup.wolf.common.util.EnumUtil;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 10:56 AM
 **/
public class BuyFlow extends AbstractTradeFlow implements TradeFlow {
    @Override
    public void buildFlow() {
        addNode(new ShopStatusCheckNode());
        addNode(new GoodsStatusCheckNode());
        addNode(new StockOperationNode());
        addNode(new UmpFlowNode());
        addNode(new OrderCreateNode());
    }


    @Override
    public PrepareResponse prepare(PrepareRequest request) {
        return null;
    }

    @Override
    public ConfirmResponse confirm(ConfirmRequest request) {
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
