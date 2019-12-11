package study.daydayup.wolf.business.goods.biz.api;

import study.daydayup.wolf.business.goods.api.dto.request.GoodsOption;
import study.daydayup.wolf.business.goods.api.entity.Goods;
import study.daydayup.wolf.business.goods.api.service.GoodsService;

import java.util.List;

/**
 * study.daydayup.wolf.business.goods.biz.api
 *
 * @author Wingle
 * @since 2019/12/11 7:38 下午
 **/
public class GoodsServiceImpl implements GoodsService {
    @Override
    public Goods findById(long goodsId, long orgId, GoodsOption option) {
        return null;
    }

    @Override
    public List<Goods> findByOrgId(long orgId, GoodsOption option) {
        return null;
    }

    @Override
    public long create(Goods goods) {
        return 0;
    }

    @Override
    public int modify(Goods goods) {
        return 0;
    }

    @Override
    public int listing(long goodsId, long orgId) {
        return 0;
    }

    @Override
    public int delisting(long goodsId, long orgId) {
        return 0;
    }
}
