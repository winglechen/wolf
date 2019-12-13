package study.daydayup.wolf.business.goods.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsLoanDO;

@Mapper
public interface GoodsLoanDAO {
    int insert(GoodsLoanDO record);

    int insertSelective(GoodsLoanDO record);

    GoodsLoanDO selectById(Long id);

    int updateByIdSelective(GoodsLoanDO record);

    int updateByGoodsIdSelective(GoodsLoanDO record);

    int updateById(GoodsLoanDO record);

    List<GoodsLoanDO> selectByGoodsIdIn(@Param("goodsIdCollection")Collection<Long> goodsIdCollection, @Param("orgId") long orgId);


}