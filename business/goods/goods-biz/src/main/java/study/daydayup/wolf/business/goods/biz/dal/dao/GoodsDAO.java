package study.daydayup.wolf.business.goods.biz.dal.dao;
import org.apache.ibatis.annotations.Param;

import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDO;

public interface GoodsDAO {
    int insert(GoodsDO record);

    int insertSelective(GoodsDO record);

    GoodsDO selectById(Long id);

    int updateByIdSelective(GoodsDO record);

    int updateById(GoodsDO record);

    int updateStateById(@Param("updatedState")Byte updatedState,@Param("id")Long id);




}