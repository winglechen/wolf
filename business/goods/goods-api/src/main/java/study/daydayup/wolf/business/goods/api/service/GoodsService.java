package study.daydayup.wolf.business.goods.api.service;

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
    BaseGoods findBaseGoodsById();
    List<BaseGoods> findBaseGoodsByOrgId();

    long create(Goods goods);
    int modify(Goods goods);
}
