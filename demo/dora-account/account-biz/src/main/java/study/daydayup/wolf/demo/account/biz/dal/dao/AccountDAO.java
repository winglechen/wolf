package study.daydayup.wolf.demo.account.biz.dal.dao;

import study.daydayup.wolf.demo.account.biz.dal.dataobject.AccountDO;
import org.apache.ibatis.annotations.Param;

/**
* Created by Mybatis Generator 2019/09/26
*/
public interface AccountDAO {

    int insert(AccountDO record);

    int updateAccountById(@Param("updatedAccount") String updatedAccount, @Param("id") Long id);

    AccountDO selectById(@Param("id") Long id);

    AccountDO selectOneByAccount(@Param("account") String account);

}