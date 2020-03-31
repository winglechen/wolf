package study.daydayup.wolf.business.uc.setting.biz.dal.dao;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.StaffSettingDO;

public interface StaffSettingDAO {
    int deleteById(Long id);

    int insert(StaffSettingDO record);

    int insertSelective(StaffSettingDO record);

    StaffSettingDO selectById(Long id);

    int updateByIdSelective(StaffSettingDO record);

    int updateByPrimaryKeyWithBLOBs(StaffSettingDO record);

    int updateById(StaffSettingDO record);
}