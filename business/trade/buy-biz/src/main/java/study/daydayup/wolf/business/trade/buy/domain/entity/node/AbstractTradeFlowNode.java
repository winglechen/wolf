package study.daydayup.wolf.business.trade.buy.domain.entity.node;

import study.daydayup.wolf.business.trade.buy.domain.entity.context.TradeFlowContext;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 11:23 AM
 **/
public abstract class AbstractTradeFlowNode implements TradeFlowNode {
    protected TradeFlowContext context;
}
