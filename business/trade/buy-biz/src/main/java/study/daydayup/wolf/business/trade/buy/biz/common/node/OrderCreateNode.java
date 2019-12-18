package study.daydayup.wolf.business.trade.buy.biz.common.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.enums.TradePhaseEnum;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;

import java.util.EnumSet;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 12:10 PM
 **/
@Component
public class OrderCreateNode extends AbstractTradeNode implements TradeNode {
    private BuyContext context;

    @Override
    public void run(BuyContext context) {
        init(context);

        if (!effectsInPhase(context.getTradePhase())) {
            return;
        }

    }

    private void init(BuyContext context) {
        this.context = context;
        phases = EnumSet.of(TradePhaseEnum.CONFIRM_PHASE);
    }
}
