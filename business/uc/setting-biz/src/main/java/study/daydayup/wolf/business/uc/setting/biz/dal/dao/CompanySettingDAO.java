package study.daydayup.wolf.business.uc.setting.biz.dal.dao;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CompanySettingDO;

public interface CompanySettingDAO {
    int deleteById(Long id);

    int insert(CompanySettingDO record);

    int insertSelective(CompanySettingDO record);

    CompanySettingDO selectById(Long id);

    int updateByIdSelective(CompanySettingDO record);

    int updateByPrimaryKeyWithBLOBs(CompanySettingDO record);

    int updateById(CompanySettingDO record);
}