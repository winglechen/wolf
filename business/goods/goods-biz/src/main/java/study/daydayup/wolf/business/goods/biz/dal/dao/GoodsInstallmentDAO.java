package study.daydayup.wolf.business.goods.biz.dal.dao;

import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsInstallmentDO;

public interface GoodsInstallmentDAO {
    int insert(GoodsInstallmentDO record);

    int insertSelective(GoodsInstallmentDO record);

    GoodsInstallmentDO selectById(Long id);

    int updateByIdSelective(GoodsInstallmentDO record);

    int updateById(GoodsInstallmentDO record);
}