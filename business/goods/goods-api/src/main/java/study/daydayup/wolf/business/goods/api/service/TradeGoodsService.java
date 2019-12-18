package study.daydayup.wolf.business.goods.api.service;

import study.daydayup.wolf.business.goods.api.dto.request.TradeGoodsRequest;
import study.daydayup.wolf.business.goods.api.dto.response.TradeGoodsResponse;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.service
 *
 * @author Wingle
 * @since 2019/12/18 11:22 上午
 **/
public interface TradeGoodsService {
    List<TradeGoodsResponse> find(List<TradeGoodsRequest> requests);
}
