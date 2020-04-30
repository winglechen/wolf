package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Goods;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.base.node.AbstractTradeNode;

import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.node
 *
 * @author Wingle
 * @since 2019/12/17 8:19 下午
 **/
@Component
public class GetSellerNode extends AbstractTradeNode implements TradeNode {

    @Override
    public void run(BuyContext context) {
        init(context);

        List<Goods> goodsList = getGoodsList();
        Goods goods = goodsList.get(0);

        Seller seller = new Seller();
        seller.setId(goods.getSellId());

        context.setSeller(seller);
        context.getRequest().setSeller(seller);
    }


}
