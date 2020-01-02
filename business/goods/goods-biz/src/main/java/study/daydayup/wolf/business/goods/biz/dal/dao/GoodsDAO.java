package study.daydayup.wolf.business.goods.biz.dal.dao;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.goods.api.vo.Loan;
import study.daydayup.wolf.business.goods.biz.dal.dataobject.GoodsDO;

@Mapper
public interface GoodsDAO {
    int insert(GoodsDO record);

    int insertSelective(GoodsDO record);

    GoodsDO selectById(@Param("id") Long id, @Param("orgId") Long orgId);

    int updateByIdSelective(GoodsDO record);

    int updateById(GoodsDO record);

    int updateStateById(@Param("id")Long id,@Param("orgId")Long orgId,@Param("updatedState")Integer updatedState);

    int updateStateByOrgId(@Param("orgId")Long orgId, @Param("oldState")Integer oldState, @Param("newState") Integer newSate);

    int updateDeleteFlagById(@Param("id")Long id,@Param("orgId")Long orgId, @Param("updatedDeleteFlag")Boolean updatedDeleteFlag);

    List<GoodsDO> selectByOrgId(@Param("orgId")Long orgId);

    GoodsDO selectOneByOrgId(@Param("orgId")Long orgId);


    List<GoodsDO> selectByIdIn(@Param("idCollection")Collection<Long> idCollection, @Param("orgId") long orgId);

    List<GoodsDO> selectSalableByIdIn(@Param("idCollection")Collection<Long> idCollection, @Param("orgId") long orgId);


}