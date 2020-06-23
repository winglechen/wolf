package study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dataobject.StaffDO;

public interface StaffDAO {
    int deleteById(Long id);

    int insert(StaffDO record);

    int insertSelective(StaffDO record);

    StaffDO selectById(Long id);

    int updateByIdSelective(StaffDO record);

    int updateById(StaffDO record);
}