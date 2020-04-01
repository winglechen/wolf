package study.daydayup.wolf.business.uc.setting.biz.dal.dao;
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

    AccountSettingDO findByAccountId(@Param("accountId")Long accountId);



}