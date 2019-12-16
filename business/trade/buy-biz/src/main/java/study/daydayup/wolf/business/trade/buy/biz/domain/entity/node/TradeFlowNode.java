package study.daydayup.wolf.business.trade.buy.biz.domain.entity.node;

import study.daydayup.wolf.business.trade.api.enums.TradePhaseEnum;
import study.daydayup.wolf.business.trade.buy.biz.domain.entity.context.TradeFlowContext;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 10:58 AM
 **/
public interface TradeFlowNode {
    void run(TradeFlowContext context);

    boolean effectsInPhase(TradePhaseEnum tradePhase);
}
