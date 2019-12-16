package study.daydayup.wolf.business.trade.buy.biz.domain.entity.flow;

import study.daydayup.wolf.business.trade.api.service.buy.BuyService;
import study.daydayup.wolf.business.trade.buy.biz.domain.entity.node.TradeFlowNode;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.flow
 *
 * @author Wingle
 * @since 2019/10/5 10:54 AM
 **/
public interface TradeFlow extends BuyService {
    void init();
    void addNode(TradeFlowNode node);
    void buildConfirmFlow();
}
