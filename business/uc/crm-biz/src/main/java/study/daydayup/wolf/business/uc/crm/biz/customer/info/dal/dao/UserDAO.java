package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.UserDO;

public interface UserDAO {
    int deleteById(Long id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectById(Long id);

    int updateByIdSelective(UserDO record);

    int updateById(UserDO record);
}