package study.daydayup.wolf.business.account.biz.api;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.enums.AccountTypeEnum;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.biz.dal.dao.AccountDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccountDO;
import study.daydayup.wolf.common.util.encrypt.Password;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;

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

        return accountDAO.insertSelective(accountDO);
    }

    @Override
    public long createSmsAccount(String mobile, String source) {
        if(null == mobile) {
            return 0;
        }

        AccountDO accountDO = new AccountDO();
        accountDO.setAccount(mobile);
        accountDO.setSource(source);
        accountDO.setAccountType((byte)AccountTypeEnum.MOBILE.getCode());
        accountDO.setCreatedAt(new Date());

        return accountDAO.insertSelective(accountDO);
    }

    @Override
    public long createPasswordAccount(@Valid PasswordRequest request) {
        AccountDO accountDO = new AccountDO();
        BeanUtils.copyProperties(request, accountDO);

        accountDO.setAccountType((byte)AccountTypeEnum.NAME.getCode());
        accountDO.setCreatedAt(new Date());

        Password pass = Password.encrypt(request.getPassword());
        accountDO.setPassword(pass.getPassword());
        accountDO.setSalt(pass.getSalt());

        return accountDAO.insertSelective(accountDO);
    }

    @Override
    public Account findByAccount(String accountName) {
        if (null == accountName) {
            return null;
        }
        AccountDO accountDO = accountDAO.selectByAccount(accountName);
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
        Account account = new Account();
        BeanUtils.copyProperties(accountDO, account);

        return account;
    }
}
