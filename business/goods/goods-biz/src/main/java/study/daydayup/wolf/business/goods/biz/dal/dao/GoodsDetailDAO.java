package study.daydayup.wolf.business.goods.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDetailDO;

@Mapper
public interface GoodsDetailDAO {
    int insert(GoodsDetailDO record);

    int insertSelective(GoodsDetailDO record);

    GoodsDetailDO selectById(Long id);

    int updateByIdSelective(GoodsDetailDO record);

    int updateById(GoodsDetailDO record);
}