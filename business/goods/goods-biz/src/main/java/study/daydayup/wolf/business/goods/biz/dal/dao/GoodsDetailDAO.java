package study.daydayup.wolf.business.goods.biz.dal.dao;

import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDetailDO;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDetailDOWithBLOBs;

public interface GoodsDetailDAO {
    int insert(GoodsDetailDOWithBLOBs record);

    int insertSelective(GoodsDetailDOWithBLOBs record);

    GoodsDetailDOWithBLOBs selectById(Long id);

    int updateByIdSelective(GoodsDetailDOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GoodsDetailDOWithBLOBs record);

    int updateById(GoodsDetailDO record);
}