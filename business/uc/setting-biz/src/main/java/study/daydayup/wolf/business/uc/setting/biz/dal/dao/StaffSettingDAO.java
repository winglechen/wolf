package study.daydayup.wolf.business.uc.setting.biz.dal.dao;

import org.apache.ibatis.annotations.Param;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.StaffSettingDO;

public interface StaffSettingDAO {
    int deleteById(Long id);

    int insert(StaffSettingDO record);

    int insertSelective(StaffSettingDO record);

    StaffSettingDO selectById(Long id);

    int updateByIdSelective(StaffSettingDO record);

    int updateByPrimaryKeyWithBLOBs(StaffSettingDO record);

    int updateById(StaffSettingDO record);

    int updateByAccountId(@Param("updated") StaffSettingDO updated, @Param("accountId")Long accountId, @Param("orgId")Long orgId);

    StaffSettingDO findByAccountId(@Param("accountId")Long accountId,@Param("orgId")Long orgId);
}