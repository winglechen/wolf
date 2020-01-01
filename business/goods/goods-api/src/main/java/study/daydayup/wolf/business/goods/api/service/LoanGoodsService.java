package study.daydayup.wolf.business.goods.api.service;

import study.daydayup.wolf.business.goods.api.dto.request.GoodsOption;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.service
 *
 * @author Wingle
 * @since 2019/12/10 8:47 下午
 **/
public interface LoanGoodsService {
    long create(LoanGoods goods);
    LoanGoods findById(long goodsId, long orgId);
    LoanGoods findById(long goodsId, long orgId, GoodsOption option);
    List<LoanGoods> findByOrgId(long orgId);
    LoanGoods findOneByOrgId(long orgId);

    int modify(LoanGoods goods);
    int remove(long goodsId, long orgId);
}
