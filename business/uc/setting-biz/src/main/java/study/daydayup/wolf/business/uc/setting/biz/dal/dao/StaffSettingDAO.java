package study.daydayup.wolf.business.uc.setting.biz.dal.dao;
import java.util.Collection;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.StaffSettingDO;

import java.util.List;

public interface StaffSettingDAO {
    int deleteById(Long id);

    int insert(StaffSettingDO record);

    int insertSelective(StaffSettingDO record);

    StaffSettingDO selectById(Long id);

    int updateByIdSelective(StaffSettingDO record);

    int updateById(StaffSettingDO record);

    int updateByAccountId(@Param("updated") StaffSettingDO updated, @Param("accountId")Long accountId, @Param("orgId")Long orgId);

    List<StaffSettingDO> findByAccountId(@Param("accountId")Long accountId,@Param("orgId")Long orgId);

    StaffSettingDO findByNamespace(@Param("namespace")String namespace,@Param("accountId")Long accountId,@Param("orgId")Long orgId);

    List<StaffSettingDO> findByNamespaceIn(@Param("namespaceCollection")Collection<String> namespaceCollection,@Param("accountId")Long accountId,@Param("orgId")Long orgId);



}