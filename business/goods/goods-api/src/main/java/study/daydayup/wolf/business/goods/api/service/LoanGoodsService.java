package study.daydayup.wolf.business.goods.api.service;

import study.daydayup.wolf.business.goods.api.dto.GoodsOption;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.goods.api.service
 *
 * @author Wingle
 * @since 2019/12/10 8:47 下午
 **/
public interface LoanGoodsService {
    Long create(LoanGoods goods);
    LoanGoods findById(Long goodsId, Long orgId);
    LoanGoods findById(Long goodsId, Long orgId, GoodsOption option);
    Page<LoanGoods> findByOrgId(Long orgId, PageRequest pageRequest);
    LoanGoods findOneByOrgId(Long orgId, Integer goodsType);

    int modify(LoanGoods goods);
    int remove(Long goodsId, Long orgId);
}
