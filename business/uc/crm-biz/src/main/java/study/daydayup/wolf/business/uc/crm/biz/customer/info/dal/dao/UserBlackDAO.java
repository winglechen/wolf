package study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao;

import study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dataobject.UserBlackDO;

public interface UserBlackDAO {
    int deleteById(Long id);

    int insert(UserBlackDO record);

    int insertSelective(UserBlackDO record);

    UserBlackDO selectById(Long id);

    int updateByIdSelective(UserBlackDO record);

    int updateById(UserBlackDO record);
}