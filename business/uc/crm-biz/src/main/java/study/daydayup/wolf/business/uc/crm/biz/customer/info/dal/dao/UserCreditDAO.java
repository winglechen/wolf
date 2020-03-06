package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.UserCreditDO;

public interface UserCreditDAO {
    int deleteById(Long id);

    int insert(UserCreditDO record);

    int insertSelective(UserCreditDO record);

    UserCreditDO selectById(Long id);

    int updateByIdSelective(UserCreditDO record);

    int updateById(UserCreditDO record);
}