package study.daydayup.wolf.business.goods.biz.api;

import study.daydayup.wolf.business.goods.api.dto.request.GoodsOption;
import study.daydayup.wolf.business.goods.api.entity.Goods;
import study.daydayup.wolf.business.goods.api.enums.GoodsStateEnum;
import study.daydayup.wolf.business.goods.api.service.GoodsService;
import study.daydayup.wolf.business.goods.biz.dal.dao.GoodsDAO;
import study.daydayup.wolf.business.goods.biz.goods.GoodsEntity;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.goods.biz.api
 *
 * @author Wingle
 * @since 2019/12/11 7:38 下午
 **/
@RpcService(protocol = "dubbo")
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsDAO goodsDAO;
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
        GoodsEntity entity = new GoodsEntity(goodsId, orgId);
        return entity.listing();
    }

    @Override
    public int delisting(long goodsId, long orgId) {
        GoodsEntity entity = new GoodsEntity(goodsId, orgId);
        return entity.delisting();
    }

    @Override
    public int delistingAll(long orgId) {
        if (orgId <= 0) {
            throw new IllegalArgumentException("orgId can't less than 1");
        }

        int oldState = GoodsStateEnum.SALABLE.getCode();
        int newState = GoodsStateEnum.UNSALABLE.getCode();

        return goodsDAO.updateStateByOrgId(orgId, oldState, newState);
    }
}
