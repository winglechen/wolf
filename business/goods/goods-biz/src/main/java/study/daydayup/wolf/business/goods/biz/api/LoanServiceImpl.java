package study.daydayup.wolf.business.goods.biz.api;

import study.daydayup.wolf.business.goods.api.dto.request.GoodsOption;
import study.daydayup.wolf.business.goods.api.entity.goods.LoanGoods;
import study.daydayup.wolf.business.goods.api.service.LoanService;
import study.daydayup.wolf.framework.rpc.RpcService;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.biz.api
 *
 * @author Wingle
 * @since 2019/12/10 8:52 下午
 **/
@RpcService(protocol = "dubbo")
public class LoanServiceImpl implements LoanService {

    @Override
    public long create(LoanGoods goods) {
        return 0;
    }

    @Override
    public LoanGoods findById(long goodsId, long orgId, GoodsOption option) {
        return null;
    }

    @Override
    public List<LoanGoods> findByOrgId(long orgId) {
        return null;
    }

    @Override
    public int modify(LoanGoods goods) {
        return 0;
    }
}
