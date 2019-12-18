package study.daydayup.wolf.business.trade.buy.biz.common.node;

import study.daydayup.wolf.business.trade.api.enums.TradePhaseEnum;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;

import java.util.EnumSet;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 11:23 AM
 **/
public abstract class AbstractTradeNode implements TradeNode {
    protected EnumSet<TradePhaseEnum> phases;
    protected BuyContext context;

    @Override
    public boolean effectsInPhase(TradePhaseEnum tradePhase) {
        if (phases == null) {
            return false;
        }

        return phases.contains(tradePhase);
    }
}
