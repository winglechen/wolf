package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.UserCreditLogDO;

public interface UserCreditLogDAO {
    int deleteById(Long id);

    int insert(UserCreditLogDO record);

    int insertSelective(UserCreditLogDO record);

    UserCreditLogDO selectById(Long id);

    int updateByIdSelective(UserCreditLogDO record);

    int updateById(UserCreditLogDO record);
}