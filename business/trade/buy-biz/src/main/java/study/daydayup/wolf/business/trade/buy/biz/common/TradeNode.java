package study.daydayup.wolf.business.trade.buy.biz.common;

import study.daydayup.wolf.common.lang.enums.trade.TradePhaseEnum;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity.node
 *
 * @author Wingle
 * @since 2019/10/5 10:58 AM
 **/
public interface TradeNode {
    void run(BuyContext context);
}
