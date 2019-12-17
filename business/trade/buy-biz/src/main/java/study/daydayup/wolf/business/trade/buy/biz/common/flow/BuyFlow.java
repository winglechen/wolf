package study.daydayup.wolf.business.trade.buy.biz.common.flow;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.buy.biz.common.AbstractTradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeFlow;
import study.daydayup.wolf.business.trade.buy.biz.common.node.*;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 10:56 AM
 **/
@Component
public class BuyFlow extends AbstractTradeFlow implements TradeFlow {
    @Override
    public void buildConfirmFlow() {
        addNode(new ShopStatusCheckNode());
        addNode(new GoodsStatusCheckNode());
        addNode(new StockOperationNode());
        addNode(new UmpNode());
        addNode(new OrderCreateNode());
    }


}
