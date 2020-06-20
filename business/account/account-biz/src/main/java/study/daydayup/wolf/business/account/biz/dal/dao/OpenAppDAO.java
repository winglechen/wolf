package study.daydayup.wolf.business.account.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import study.daydayup.wolf.business.account.biz.dal.dataobject.OpenAppDO;

public interface OpenAppDAO {
    int deleteById(Long id);

    int insert(OpenAppDO record);

    int insertSelective(OpenAppDO record);

    OpenAppDO selectById(Long id);

    int updateByIdSelective(OpenAppDO record);

    int updateById(OpenAppDO record);

    List<OpenAppDO> selectByOrgId(@Param("orgId")Long orgId);

    OpenAppDO selectByOrgIdAndAppType(@Param("orgId")Long orgId,@Param("appType")Integer appType);

    int updateByOrgIdAndAppType(@Param("updated")OpenAppDO updated,@Param("orgId")Long orgId,@Param("appType")Integer appType);

    Long countByOrgIdAndAppType(@Param("orgId")Long orgId,@Param("appType")Integer appType);



}