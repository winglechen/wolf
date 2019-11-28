package study.daydayup.wolf.business.account.biz.api;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.biz.dal.dao.AccountDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccountDO;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/11/19 3:44 下午
 **/
@RpcService(protocol = "dubbo")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDAO accountDAO;

    public long create(Account account) {
        return 123;
    }

    public Account findByAccount(String accountName) {
        if (null == accountName) {
            return null;
        }
        AccountDO accountDO = accountDAO.selectByAccount(accountName);
        Account account = new Account();
        BeanUtils.copyProperties(accountDO, account);

        return account;
    }

    public Account findById(long id) {
        if (id < 0) {
            return null;
        }
        AccountDO accountDO = accountDAO.selectById(id);
        Account account = new Account();
        BeanUtils.copyProperties(accountDO, account);

        return account;
    }
}
