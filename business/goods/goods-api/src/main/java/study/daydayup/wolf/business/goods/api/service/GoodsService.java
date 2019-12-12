package study.daydayup.wolf.business.goods.api.service;

import study.daydayup.wolf.business.goods.api.dto.request.GoodsOption;
import study.daydayup.wolf.business.goods.api.entity.Goods;
import study.daydayup.wolf.business.goods.api.entity.goods.BaseGoods;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.service
 *
 * @author Wingle
 * @since 2019/10/29 12:12 上午
 **/
public interface GoodsService {
    Goods findById(long goodsId, long orgId, GoodsOption option);
    List<Goods> findByOrgId(long orgId, GoodsOption option);

    long create(Goods goods);
    int modify(Goods goods);

    int listing(long goodsId, long orgId);
    int delisting(long goodsId, long orgId);
    int delistingAll(long orgId);
}
