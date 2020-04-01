package study.daydayup.wolf.business.uc.setting.biz.dal.dao;
import java.util.Collection;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.AccountSettingDO;

public interface AccountSettingDAO {
    int deleteById(Long id);

    int insert(AccountSettingDO record);

    int insertSelective(AccountSettingDO record);

    AccountSettingDO selectById(Long id);

    int updateByIdSelective(AccountSettingDO record);

    int updateById(AccountSettingDO record);

    int updateByAccountId(@Param("updated")AccountSettingDO updated,@Param("accountId")Long accountId);

    List<AccountSettingDO> findByAccountId(@Param("accountId")Long accountId);

    AccountSettingDO findByNamespace(@Param("namespace")String namespace, @Param("accountId")Long accountId);

    List<AccountSettingDO> findByNamespaceIn(@Param("namespaceCollection")Collection<String> namespaceCollection, @Param("accountId")Long accountId);

}