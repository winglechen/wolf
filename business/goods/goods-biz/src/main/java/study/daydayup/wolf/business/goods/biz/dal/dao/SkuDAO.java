package study.daydayup.wolf.business.goods.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.SkuDO;

@Mapper
public interface SkuDAO {
    int insert(SkuDO record);

    int insertSelective(SkuDO record);

    SkuDO selectById(Long id);

    int updateByIdSelective(SkuDO record);

    int updateById(SkuDO record);
}