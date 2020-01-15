package study.daydayup.wolf.business.goods.api.service;

import study.daydayup.wolf.business.goods.api.dto.GoodsOption;
import study.daydayup.wolf.business.goods.api.entity.Goods;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.service
 *
 * @author Wingle
 * @since 2019/10/29 12:12 上午
 **/
public interface GoodsService {
    Goods findById(Long goodsId, Long orgId, GoodsOption option);
    List<Goods> findByOrgId(Long orgId, GoodsOption option);

    Long create(Goods goods);
    int modify(Goods goods);
    int remove(Long goodsId, Long orgId);

    int listing(Long goodsId, Long orgId);
    int delisting(Long goodsId, Long orgId);
    int delistingAll(Long orgId);
}
