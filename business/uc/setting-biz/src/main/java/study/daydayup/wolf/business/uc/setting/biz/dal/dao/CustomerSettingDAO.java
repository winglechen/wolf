package study.daydayup.wolf.business.uc.setting.biz.dal.dao;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CustomerSettingDO;

public interface CustomerSettingDAO {
    int deleteById(Long id);

    int insert(CustomerSettingDO record);

    int insertSelective(CustomerSettingDO record);

    CustomerSettingDO selectById(Long id);

    int updateByIdSelective(CustomerSettingDO record);

    int updateByPrimaryKeyWithBLOBs(CustomerSettingDO record);

    int updateById(CustomerSettingDO record);
}