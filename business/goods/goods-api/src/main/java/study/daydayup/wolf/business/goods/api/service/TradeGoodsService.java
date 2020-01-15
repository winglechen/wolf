package study.daydayup.wolf.business.goods.api.service;

import study.daydayup.wolf.business.goods.api.dto.GoodsOption;
import study.daydayup.wolf.business.goods.api.dto.trade.TradeGoodsRequest;
import study.daydayup.wolf.business.goods.api.dto.trade.TradeGoodsResponse;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.service
 *
 * @author Wingle
 * @since 2019/12/18 11:22 上午
 **/
public interface TradeGoodsService {
    List<TradeGoodsResponse> find(List<TradeGoodsRequest> requests);
    List<TradeGoodsResponse> find(List<TradeGoodsRequest> requests, GoodsOption option);
}
