package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.buy.base.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.domain.exception.buy.GoodsNotFoundException;
import study.daydayup.wolf.business.trade.api.domain.vo.buy.Goods;
import study.daydayup.wolf.business.trade.buy.biz.base.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.base.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.base.node.AbstractTradeNode;
import study.daydayup.wolf.business.trade.buy.biz.epi.GoodsEpi;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Override
    public void run(BuyContext context) {
        init(context);

        List<Goods> goodsList = goodsEpi.fetch(
                context.getRequest().getGoods()
        );
        validGoodsList(goodsList);
        mergeRequestToGoods(goodsList);

        context.setGoodsList(goodsList);
    }

    private void validGoodsList(List<Goods> goodsList) {
        if (goodsList == null || goodsList.isEmpty()) {
            throw new GoodsNotFoundException();
        }

        List<GoodsRequest> goodsRequests = context.getRequest().getGoods();
        if (goodsList.size() != goodsRequests.size()) {
            throw new GoodsNotFoundException();
        }
    }

    private void mergeRequestToGoods(List<Goods> goodsList) {
        List<GoodsRequest> goodsRequests = context.getRequest().getGoods();
        Map<Long, GoodsRequest> goodsRequestMap = toGoodsRequestMap(goodsRequests);

        for (Goods goods : goodsList) {
            GoodsRequest goodsRequest =goodsRequestMap.get(goods.getGoodsId());
            goods.setQuantity(goodsRequest.getQuantity());
            goods.setGiftFlag(goodsRequest.getGiftFlag());
            goods.setPromotionId(goodsRequest.getPromotionId());
            goods.setMemo(goodsRequest.getMemo());

            goods.setPayPrice(goods.getSalePrice());
        }
    }

    private Map<Long, GoodsRequest> toGoodsRequestMap(List<GoodsRequest> goodsRequests) {
        return goodsRequests .stream() .collect(
                    Collectors.toMap(GoodsRequest::getGoodsId, Function.identity())
                );
    }

}
