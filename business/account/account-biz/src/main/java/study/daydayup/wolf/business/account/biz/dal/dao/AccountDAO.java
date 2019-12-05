package study.daydayup.wolf.business.account.biz.dal.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Collection;

import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccountDO;

public interface AccountDAO {
    int deleteById(Long id);

    int insert(AccountDO record);

    int insertSelective(AccountDO record);

    AccountDO selectById(Long id);

    int updateByIdSelective(AccountDO record);

    int updateById(AccountDO record);

    List<AccountDO> selectByIdIn(@Param("idCollection")Collection<Long> idCollection);

    AccountDO selectByAccount(@Param("account")String account);



}



