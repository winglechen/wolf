package study.daydayup.wolf.business.account.biz.api;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.exception.AccountNotFoundException;
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

    @Override
    public long create(Account account) {
        if (null == account) {
            return 0;
        }

        AccountDO accountDO = new AccountDO();
        BeanUtils.copyProperties(account, accountDO);

        accountDAO.insertSelective(accountDO);
        if (null == accountDO.getId()) {
            return 0;
        }
        return accountDO.getId();
    }

    private AccountDO selectByAccount(String accountName) {
        if (null == accountName) {
            return null;
        }

        AccountDO accountDO = accountDAO.selectByAccount(accountName);
        if (accountDO == null || null == accountDO.getId()) {
            throw new AccountNotFoundException();
        }

        return accountDO;
    }

    @Override
    public Account findByAccount(String accountName) {
        AccountDO accountDO = selectByAccount(accountName);
        Account account = new Account();
        BeanUtils.copyProperties(accountDO, account);

        return account;
    }

    @Override
    public long existByAccount(String account) {
        if (null == account) {
            return 0;
        }

        Long id = accountDAO.selectIdByAccount(account);
        if (null != id) {
            return (long)id;
        }

        return 0;
    }

    @Override
    public Account findById(long id) {
        if (id < 0) {
            return null;
        }
        AccountDO accountDO = accountDAO.selectById(id);
        if (accountDO == null) {
            return null;
        }

        Account account = new Account();
        BeanUtils.copyProperties(accountDO, account);
        account.setAccountType(account.getAccountType());

        return account;
    }
}
