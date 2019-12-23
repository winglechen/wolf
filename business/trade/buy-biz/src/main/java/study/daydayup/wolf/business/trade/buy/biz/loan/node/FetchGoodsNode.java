package study.daydayup.wolf.business.trade.buy.biz.loan.node;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.trade.api.dto.buy.request.GoodsRequest;
import study.daydayup.wolf.business.trade.api.exception.buy.GoodsNotFoundException;
import study.daydayup.wolf.business.trade.api.vo.buy.TradeGoods;
import study.daydayup.wolf.business.trade.buy.biz.common.context.BuyContext;
import study.daydayup.wolf.business.trade.buy.biz.common.TradeNode;
import study.daydayup.wolf.business.trade.buy.biz.common.node.AbstractTradeNode;
import study.daydayup.wolf.business.trade.buy.biz.epi.GoodsEpi;

import javax.annotation.Resource;
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

        List<TradeGoods> goodsList = goodsEpi.fetch(
                context.getRequest().getGoodsRequestList()
        );
        validGoodsList(goodsList);
        mergeRequestToGoods(goodsList);

        context.setGoodsList(goodsList);
    }

    private void validGoodsList(List<TradeGoods> goodsList) {
        if (goodsList == null || goodsList.isEmpty()) {
            throw new GoodsNotFoundException();
        }

        List<GoodsRequest> goodsRequests = context.getRequest().getGoodsRequestList();
        if (goodsList.size() != goodsRequests.size()) {
            throw new GoodsNotFoundException();
        }
    }

    private void mergeRequestToGoods(List<TradeGoods> goodsList) {
        List<GoodsRequest> goodsRequests = context.getRequest().getGoodsRequestList();
        Map<Long, GoodsRequest> goodsRequestMap = toGoodsRequestMap(goodsRequests);

        for (TradeGoods goods : goodsList) {
            GoodsRequest goodsRequest =goodsRequestMap.get(goods.getGoodsId());
            goods.setQuantity(goodsRequest.getQuantity());
            goods.setGiftFlag(goodsRequest.getGiftFlag());
            goods.setPromotionId(goodsRequest.getPromotionId());
            goods.setMemo(goodsRequest.getMemo());

            goods.setPayPrice(goods.getSalePrice());
            goods.setPostage(0);
        }
    }

    private Map<Long, GoodsRequest> toGoodsRequestMap(List<GoodsRequest> goodsRequests) {
        return goodsRequests .stream() .collect(
                    Collectors.toMap(GoodsRequest::getGoodsId, Function.identity())
                );
    }

}
