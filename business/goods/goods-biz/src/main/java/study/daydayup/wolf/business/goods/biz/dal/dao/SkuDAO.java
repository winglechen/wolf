package study.daydayup.wolf.business.goods.biz.dal.dao;

import study.daydayup.wolf.business.goods.biz.dal.dataobject.SkuDO;

public interface SkuDAO {
    int insert(SkuDO record);

    int insertSelective(SkuDO record);

    SkuDO selectById(Long id);

    int updateByIdSelective(SkuDO record);

    int updateById(SkuDO record);
}