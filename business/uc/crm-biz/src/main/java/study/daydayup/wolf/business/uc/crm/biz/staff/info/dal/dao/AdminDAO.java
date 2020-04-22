package study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dataobject.AdminDO;

public interface AdminDAO {
    int deleteById(Long id);

    int insert(AdminDO record);

    int insertSelective(AdminDO record);

    AdminDO selectById(Long id);

    int updateByIdSelective(AdminDO record);

    int updateById(AdminDO record);
}