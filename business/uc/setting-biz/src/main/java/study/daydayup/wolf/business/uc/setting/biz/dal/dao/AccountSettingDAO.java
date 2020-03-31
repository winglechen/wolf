package study.daydayup.wolf.business.uc.setting.biz.dal.dao;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.AccountSettingDO;

public interface AccountSettingDAO {
    int deleteById(Long id);

    int insert(AccountSettingDO record);

    int insertSelective(AccountSettingDO record);

    AccountSettingDO selectById(Long id);

    int updateByIdSelective(AccountSettingDO record);

    int updateByPrimaryKeyWithBLOBs(AccountSettingDO record);

    int updateById(AccountSettingDO record);
}