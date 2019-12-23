package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.exception.buy.GoodsNotFoundException;
import study.daydayup.wolf.business.trade.api.vo.buy.Seller;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeGoods;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.common.node.AbstractTradeNode;
import study.daydayup.wolf.business.trade.buy.biz.epi.GoodsEpi;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.trade.buy.biz.loan.node
 *
 * @author Wingle
 * @since 2019/12/17 8:19 下午
 **/
@Component
public class GetSellerNode extends AbstractTradeNode implements TradeNode {
    @Resource
    private GoodsEpi goodsEpi;


    @Override
    public void run(BuyContext context) {
        init(context);

        List<TradeGoods> goodsList = getGoodsList();
        TradeGoods goods = goodsList.get(0);

        Seller seller = new Seller();
        seller.setId(goods.getSellId());

        context.setSeller(seller);
    }


}
