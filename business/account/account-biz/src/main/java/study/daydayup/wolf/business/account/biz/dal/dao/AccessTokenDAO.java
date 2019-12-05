package study.daydayup.wolf.business.account.biz.dal.dao;

import study.daydayup.wolf.business.account.biz.dal.dataobject.AccessTokenDO;

public interface AccessTokenDAO {
    int deleteById(Long id);

    int insert(AccessTokenDO record);

    int insertSelective(AccessTokenDO record);

    AccessTokenDO selectById(Long id);

    int updateByIdSelective(AccessTokenDO record);

    int updateById(AccessTokenDO record);
}