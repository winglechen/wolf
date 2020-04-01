package study.daydayup.wolf.business.uc.setting.biz.dal.dao;
import java.util.Collection;
import org.apache.ibatis.annotations.Param;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CompanySettingDO;

import java.util.List;

public interface CompanySettingDAO {
    int deleteById(Long id);

    int insert(CompanySettingDO record);

    int insertSelective(CompanySettingDO record);

    CompanySettingDO selectById(Long id);

    int updateByIdSelective(CompanySettingDO record);

    int updateById(CompanySettingDO record);

    int updateByOrgId(@Param("updated")CompanySettingDO updated,@Param("orgId")Long orgId);

    List<CompanySettingDO> findByOrgId(@Param("orgId")Long orgId);

    CompanySettingDO findByNamespace(@Param("namespace")String namespace, @Param("orgId")Long orgId);

    List<CompanySettingDO> findByNamespaceIn(@Param("namespaceCollection")Collection<String> namespaceCollection, @Param("orgId")Long orgId);


}