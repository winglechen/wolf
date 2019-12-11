package study.daydayup.wolf.business.goods.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDO;

@Mapper
public interface GoodsDAO {
    int insert(GoodsDO record);

    int insertSelective(GoodsDO record);

    GoodsDO selectById(Long id);

    int updateByIdSelective(GoodsDO record);

    int updateById(GoodsDO record);
}