package study.daydayup.wolf.business.account.biz.dal.dao;

import org.apache.ibatis.annotations.Mapper;
import study.daydayup.wolf.business.account.biz.dal.dao.auto.AccountAutoDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccountDO;

/**
 * study.daydayup.wolf.business.account.biz.dal.dao
 *
 * @author Wingle
 * @since 2019/11/21 2:56 下午
 **/
@Mapper
public interface AccountDAO extends AccountAutoDAO {
    AccountDO selectByAccount(String account);
}
