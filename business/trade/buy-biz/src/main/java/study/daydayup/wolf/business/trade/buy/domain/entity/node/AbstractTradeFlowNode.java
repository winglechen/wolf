package study.daydayup.wolf.business.trade.buy.domain.entity.node;

import study.daydayup.wolf.business.trade.api.enums.TradePhaseEnum;
import study.daydayup.wolf.business.trade.buy.domain.entity.context.TradeFlowContext;

import java.util.EnumSet;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 11:23 AM
 **/
public abstract class AbstractTradeFlowNode implements TradeFlowNode {
    protected EnumSet<TradePhaseEnum> phases;
    protected TradeFlowContext context;


    @Override
    public boolean effectsInPhase(TradePhaseEnum tradePhase) {
        if (phases == null) {
            return false;
        }

        return phases.contains(tradePhase);
    }
}
