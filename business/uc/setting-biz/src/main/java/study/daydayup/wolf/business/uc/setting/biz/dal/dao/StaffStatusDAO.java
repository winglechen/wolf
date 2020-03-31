package study.daydayup.wolf.business.uc.setting.biz.dal.dao;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.StaffStatusDO;

public interface StaffStatusDAO {
    int deleteById(Long id);

    int insert(StaffStatusDO record);

    int insertSelective(StaffStatusDO record);

    StaffStatusDO selectById(Long id);

    int updateByIdSelective(StaffStatusDO record);

    int updateById(StaffStatusDO record);
}