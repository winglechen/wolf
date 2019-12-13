package study.daydayup.wolf.business.goods.biz.goods;

import lombok.Builder;
import study.daydayup.wolf.business.goods.api.entity.Goods;
import study.daydayup.wolf.business.goods.api.enums.GoodsStateEnum;
import study.daydayup.wolf.business.goods.api.exception.InvalidGoodsIdException;
import study.daydayup.wolf.business.goods.biz.dal.dao.GoodsDAO;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.goods.biz.entity
 *
 * @author Wingle
 * @since 2019/10/3 11:14 PM
 **/
public class GoodsEntity extends Goods {
    @Resource
    private GoodsDAO goodsDAO;
    @Builder
    public GoodsEntity(){};

    public GoodsEntity(long id, long orgId) {
        if (id <= 0) {
            throw new InvalidGoodsIdException(id);
        }

        this.id = id;
        if (orgId > 0) {
            this.orgId = orgId;
        }
    }

    public int listing() {
        GoodsStateEnum state = GoodsStateEnum.SALABLE;
        return goodsDAO.updateStateById(id, orgId, state.getCode());
    }

    public int delisting() {
        GoodsStateEnum state = GoodsStateEnum.UNSALABLE;
        return goodsDAO.updateStateById(id, orgId, state.getCode());
    }

    public int trash() {
        return goodsDAO.updateDeleteFlagById(id, orgId, true);
    }

}
