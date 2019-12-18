package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.node.AbstractTradeNode;
import study.daydayup.wolf.business.trade.buy.biz.epi.GoodsEpi;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.node
 *
 * @author Wingle
 * @since 2019/12/17 8:19 下午
 **/
@Component
public class FetchGoodsNode extends AbstractTradeNode implements TradeNode {
    @Resource
    private GoodsEpi goodsEpi;

    private BuyContext context;

    @Override
    public void run(BuyContext context) {
        init(context);

    }

    private void init(BuyContext context) {
        this.context = context;
    }
}
