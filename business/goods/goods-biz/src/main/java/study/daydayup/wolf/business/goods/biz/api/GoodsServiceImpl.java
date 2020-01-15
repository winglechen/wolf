package study.daydayup.wolf.business.goods.biz.api;

import study.daydayup.wolf.business.goods.api.dto.GoodsOption;
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
    @Resource
    private GoodsEntity goodsEntity;
    @Override
    public Goods findById(Long  goodsId, Long  orgId, GoodsOption option) {
        return null;
    }

    @Override
    public List<Goods> findByOrgId(Long  orgId, GoodsOption option) {
        return null;
    }

    @Override
    public Long  create(Goods goods) {
        return 0L;
    }

    @Override
    public int modify(Goods goods) {
        return 0;
    }

    @Override
    public int remove(Long  goodsId, Long  orgId) {
        goodsEntity.init(goodsId, orgId);
        return goodsEntity.trash();
    }

    @Override
    public int listing(Long  goodsId, Long  orgId) {
        goodsEntity.init(goodsId, orgId);
        return goodsEntity.listing();
    }

    @Override
    public int delisting(Long  goodsId, Long  orgId) {
        goodsEntity.init(goodsId, orgId);
        return goodsEntity.delisting();
    }

    @Override
    public int delistingAll(Long  orgId) {
        if (orgId <= 0) {
            throw new IllegalArgumentException("orgId can't less than 1");
        }

        int oldState = GoodsStateEnum.SALABLE.getCode();
        int newState = GoodsStateEnum.UNSALABLE.getCode();

        return goodsDAO.updateStateByOrgId(orgId, oldState, newState);
    }
}
