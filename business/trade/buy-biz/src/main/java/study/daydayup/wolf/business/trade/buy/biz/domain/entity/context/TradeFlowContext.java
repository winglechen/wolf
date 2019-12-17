package study.daydayup.wolf.business.trade.buy.biz.domain.entity.context;

import study.daydayup.wolf.business.trade.api.vo.buy.Buyer;
import study.daydayup.wolf.business.trade.buy.biz.domain.entity.Seller;
import study.daydayup.wolf.business.trade.buy.biz.domain.entity.TradeGoods;

/**
 * study.daydayup.wolf.business.trade.buy.domain.entity
 *
 * @author Wingle
 * @since 2019/10/5 11:01 AM
 **/
public class TradeFlowContext {
    private Buyer buyer;
    private Seller seller;
    private TradeGoods[] goods;
}