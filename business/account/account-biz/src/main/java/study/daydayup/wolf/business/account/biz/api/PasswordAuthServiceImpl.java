package study.daydayup.wolf.business.account.biz.api;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.enums.AccountTypeEnum;
import study.daydayup.wolf.business.account.api.exception.AccountExistsException;
import study.daydayup.wolf.business.account.api.exception.AccountNotFoundException;
import study.daydayup.wolf.business.account.api.exception.AuthFailedException;
import study.daydayup.wolf.business.account.api.exception.CreateLicenseFailedException;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.api.service.auth.PasswordAuthService;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.biz.dal.dao.AccountDAO;
import study.daydayup.wolf.business.account.biz.dal.dataobject.AccountDO;
import study.daydayup.wolf.common.util.encrypt.password.Password;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 6:01 下午
 **/
@RpcService(protocol = "dubbo")
public class PasswordAuthServiceImpl implements PasswordAuthService {
    @Resource
    private AccountDAO accountDAO;
    @Resource
    private AccountService accountService;
    @Reference
    private OauthLicenseService licenseService;

    @Override
    public Result<Long> register(@Validated PasswordRequest request) {
        long accountId = accountService.existByAccount(request.getAccount());
        if (accountId > 0) {
            throw new AccountExistsException();
        }

        accountId = createPasswordAccount(request);
        return Result.ok(accountId);
    }

    @Override
    public Result<Object> changePassword(@Validated PasswordRequest request) {
        if (null == request.getNewPassword()) {
            throw new IllegalArgumentException("new password can't be null");
        }

        AccountDO accountDO = selectByAccount(request.getAccount());
        if (accountDO == null) {
            throw new AccountNotFoundException();
        }

        String salt = accountDO.getSalt();
        if (!verifyPassword(salt, accountDO.getPassword(), request.getPassword())) {
            throw new AuthFailedException();
        }

        saveNewPassword(request, accountDO);
        return Result.ok();
    }

    @Override
    public Result<OauthLicense> login(@Validated PasswordRequest request) {
        long accountId = verifyPasswordAccount(request);

        if (0 == accountId) {
            throw new AccountNotFoundException();
        }

        return Result.ok(createLicense(accountId, request));
    }

    @Override
    public Result<OauthLicense> registerAndLogin(@Validated PasswordRequest request) {
        long accountId = verifyPasswordAccount(request);

        if (0 == accountId) {
            accountId = createPasswordAccount(request);
        }

        return Result.ok(createLicense(accountId, request));
    }

    public long createPasswordAccount(@Valid PasswordRequest request) {
        AccountDO accountDO = new AccountDO();
        BeanUtils.copyProperties(request, accountDO);

        accountDO.setAccountType((byte) AccountTypeEnum.NAME.getCode());
        accountDO.setCreatedAt(new Date());

        String salt = Password.createSalt();
        String pass = Password.encrypt(request.getPassword(), salt);
        accountDO.setPassword(pass);
        accountDO.setSalt(salt);

        accountDAO.insertSelective(accountDO);
        if (null == accountDO.getId()) {
            return 0;
        }
        return accountDO.getId();
    }

    public long verifyPasswordAccount(@Validated PasswordRequest request) {
        String accountName = request.getAccount();
        String password = request.getPassword();
        if (null == accountName || null == password) {
            return 0;
        }

        AccountDO accountDO = selectByAccount(accountName);
        if (null == accountDO) {
            return 0;
        }

        if (StringUtil.isBlank(accountDO.getPassword()) && StringUtil.notBlank(request.getNewPassword())) {
            saveNewPassword(request, accountDO);
        }

        if (!verifyPassword(accountDO.getSalt(), accountDO.getPassword(), password)) {
            throw new AuthFailedException();
        }

        return accountDO.getId();
    }

    private AccountDO selectByAccount(String accountName) {
        if (null == accountName) {
            return null;
        }

        return accountDAO.selectByAccount(accountName);
    }

    private void saveNewPassword(PasswordRequest request, AccountDO accountDO) {
        String newSalt = Password.createSalt();
        String encryptPassword = Password.encrypt(request.getNewPassword(), newSalt);

        AccountDO newAccountDO = new AccountDO();
        newAccountDO.setId(accountDO.getId());
        newAccountDO.setPassword(encryptPassword);
        newAccountDO.setSalt(newSalt);

        accountDAO.updateByIdSelective(newAccountDO);
    }

    private boolean verifyPassword(String salt, String realPassword, String password) {
        if (StringUtil.isBlank(realPassword)) {
            return true;
        }

        String encryptPassword = Password.encrypt(password, salt);
        return encryptPassword.equals(realPassword);
    }


    private OauthLicense createLicense(Long accountId, PasswordRequest request) {
        if (accountId == null || accountId <= 0) {
            throw new CreateLicenseFailedException();
        }

        LicenseRequest licenseRequest = new LicenseRequest();
        BeanUtils.copyProperties(request, licenseRequest);
        licenseRequest.setAccountId(accountId);

        OauthLicense license = licenseService.grant(licenseRequest);
        if (license == null) {
            throw new CreateLicenseFailedException();
        }
        return license;
    }

}
