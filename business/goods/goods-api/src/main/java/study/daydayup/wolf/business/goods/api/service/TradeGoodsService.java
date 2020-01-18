package study.daydayup.wolf.business.goods.api.service;

import study.daydayup.wolf.business.goods.api.dto.GoodsOption;
import study.daydayup.wolf.business.goods.api.dto.trade.TradeGoodsRequest;
import study.daydayup.wolf.business.goods.api.dto.trade.GoodsDTO;
import study.daydayup.wolf.framework.rpc.Result;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.service
 *
 * @author Wingle
 * @since 2019/12/18 11:22 上午
 **/
public interface TradeGoodsService {
    Result<List<GoodsDTO>> find(List<TradeGoodsRequest> requests);
    Result<List<GoodsDTO>> find(List<TradeGoodsRequest> requests, GoodsOption option);
}
