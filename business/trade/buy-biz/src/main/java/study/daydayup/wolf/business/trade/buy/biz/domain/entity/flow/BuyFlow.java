package study.daydayup.wolf.business.trade.buy.biz.domain.entity.flow;

import study.daydayup.wolf.business.trade.buy.biz.domain.entity.node.*;
import study.daydayup.wolf.business.trade.buy.domain.entity.node.*;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 10:56 AM
 **/
public class BuyFlow extends AbstractTradeFlow implements TradeFlow {
    @Override
    public void buildConfirmFlow() {
        addNode(new ShopStatusCheckNode());
        addNode(new GoodsStatusCheckNode());
        addNode(new StockOperationNode());
        addNode(new UmpFlowNode());
        addNode(new OrderCreateNode());
    }


}
