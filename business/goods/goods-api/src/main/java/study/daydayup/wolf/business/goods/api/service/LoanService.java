package study.daydayup.wolf.business.goods.api.service;

import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.api.service
 *
 * @author Wingle
 * @since 2019/12/10 8:47 下午
 **/
public interface LoanService {
    long create(LoanGoods goods);
    int modify(LoanGoods goods);
    List<LoanGoods> findByOrgId(long orgId);
}
