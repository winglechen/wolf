package study.daydayup.wolf.business.uc.setting.biz.dal.dao;

import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.AccountStatusDO;

public interface AccountStatusDAO {
    int deleteById(Long id);

    int insert(AccountStatusDO record);

    int insertSelective(AccountStatusDO record);

    AccountStatusDO selectById(Long id);

    int updateByIdSelective(AccountStatusDO record);

    int updateById(AccountStatusDO record);
}