package study.daydayup.wolf.business.trade.buy.biz.common.node;

import study.daydayup.wolf.business.trade.api.enums.TradePhaseEnum;
import study.daydayup.wolf.business.trade.api.exception.buy.GoodsNotFoundException;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeGoods;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;

import java.util.EnumSet;
import java.util.List;

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

    protected List<TradeGoods> getGoodsList() {
        List<TradeGoods> goodsList = context.getGoodsList();
        if (goodsList == null || goodsList.isEmpty()) {
            throw new GoodsNotFoundException();
        }

        return goodsList;
    }

}
