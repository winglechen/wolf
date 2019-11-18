package study.daydayup.wolf.demo.account.biz.service.impl;

import study.daydayup.wolf.demo.account.api.dto.request.account.AccountRequest;
import study.daydayup.wolf.demo.account.api.enums.AccountSourceEnum;
import study.daydayup.wolf.demo.account.api.enums.AccountTypeEnum;
import study.daydayup.wolf.demo.account.api.enums.GenderEnum;
import study.daydayup.wolf.demo.account.api.exception.AccountCreateException;
import study.daydayup.wolf.demo.account.api.exception.AccountExistedException;
import study.daydayup.wolf.demo.account.api.exception.AccountNotFindException;
import study.daydayup.wolf.demo.account.api.entity.Account;
import study.daydayup.wolf.demo.account.biz.dal.dao.AccountDAO;
import study.daydayup.wolf.demo.account.biz.dal.dataobject.AccountDO;
import study.daydayup.wolf.demo.account.biz.service.AccountInnerService;
//import study.daydayup.wolf.common.util.encrypt.ShaEncrypt;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class AccountInnerServiceImpl implements AccountInnerService {

    private final static String salt = "sf23jdcNsdsx2uUknc32k";

    @Resource
    private AccountDAO accountDAO;

    @Override
    public Boolean changeAccountById(Long id, String account) {
        int update = accountDAO.updateAccountById(account, id);
        return update > 0;
    }

    @Override
    public Account create(AccountRequest accountRequest) {
        AccountDO accountDO = accountDAO.selectOneByAccount(accountRequest.getAccount());
        if (accountDO != null) {
            throw new AccountExistedException("该账号已存在");
        }
        accountDO = new AccountDO();
        accountDO.setAccount(accountRequest.getAccount());
        accountDO.setType(accountRequest.getType());
        accountDO.setSource(accountRequest.getSource());
        accountDO.setPassword(StringUtils.isBlank(accountRequest.getPassword()) ? "" : encrypt(accountRequest.getPassword()));
        accountDO.setNickname(accountRequest.getNickname());
        accountDO.setGender(accountRequest.getGender());
        accountDO.setAvatar(accountRequest.getAvatar());
        accountDO.setVersion(1);
        accountDO.setDeleteFlag(0);
        accountDO.setLastEditor(0L);
        accountDO.setCreateTime(new Date());
        accountDO.setUpdateTime(new Date());
        int insert = accountDAO.insert(accountDO);
        if (insert <= 0) {
            throw new AccountCreateException("创建账号失败");
        }

        return formatAccountDTO(accountDO);
    }

    private String encrypt(String unencrypted) {
        String uniqueStr = salt + unencrypted;
        return uniqueStr;
//        try {
//            return ShaEncrypt.sha512(uniqueStr).substring(0, 30);
//        } catch (Exception e) {
//            throw new AccountCreateException("账号加密失败");
//        }
    }

    @Override
    public Account getByAccount(String account) {
        AccountDO accountDO = accountDAO.selectOneByAccount(account);
        if (accountDO == null) {
            throw new AccountNotFindException("该账号不存在");
        }
        return formatAccountDTO(accountDO);
    }

    @Override
    public Account getById(Long id) {
        AccountDO accountDO = accountDAO.selectById(id);
        if (accountDO == null) {
            throw new AccountNotFindException("该账号不存在");
        }
        return formatAccountDTO(accountDO);
    }

    @Override
    public Account getWithCreateAccount(String mobile) {
        Account account = null;
        try {
            account = getByAccount(mobile);
        }catch (Exception e) {
        }
        if (account != null) {
            return account;
        }
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccount(mobile);
        accountRequest.setType(AccountTypeEnum.MOBILE.getType());
        accountRequest.setSource(AccountSourceEnum.QIE_H5.getSource());
        accountRequest.setPassword("");
        accountRequest.setNickname(mobile);
        accountRequest.setGender(GenderEnum.UNKNOWN.getCode());
        accountRequest.setAvatar("");
        return create(accountRequest);
    }

    private Account formatAccountDTO(AccountDO accountDO) {
        Account account = new Account();
        account.setId(accountDO.getId());
        account.setAccount(accountDO.getAccount());
        return account;
    }
}
