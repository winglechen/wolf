package study.daydayup.wolf.business.goods.biz.dal.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDO;

@Mapper
public interface GoodsDAO {
    Long insert(GoodsDO record);

    Long insertSelective(GoodsDO record);

    GoodsDO selectById(@Param("id") Long id, @Param("orgId") Long orgId);

    int updateByIdSelective(GoodsDO record);

    int updateById(GoodsDO record);

    int updateStateById(@Param("id")Long id,@Param("orgId")Long orgId,@Param("updatedState")Integer updatedState);

    List<GoodsDO> selectByOrgId(@Param("orgId")Long orgId);

}