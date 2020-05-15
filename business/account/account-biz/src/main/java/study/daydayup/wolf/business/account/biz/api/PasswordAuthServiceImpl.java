package study.daydayup.wolf.business.account.biz.api;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.exception.AccountExistsException;
import study.daydayup.wolf.business.account.api.exception.AccountNotFoundException;
import study.daydayup.wolf.business.account.api.exception.CreateLicenseFailedException;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.api.service.auth.PasswordAuthService;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 6:01 下午
 **/
@RpcService(protocol = "dubbo")
public class PasswordAuthServiceImpl implements PasswordAuthService {
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

        accountId = accountService.createPasswordAccount(request);
        return Result.ok(accountId);
    }

    @Override
    public Result changePassword(@Validated PasswordRequest request) {
        if (null == request.getNewPassword()) {
            throw new IllegalArgumentException("new password can't be null");
        }

        accountService.changePassword(request);
        return Result.ok();
    }

    @Override
    public Result<OauthLicense> login(@Validated PasswordRequest request) {
        long accountId = accountService.verifyPasswordAccount(request);

        if (0 == accountId) {
            throw new AccountNotFoundException();
        }

        return Result.ok(createLicense(accountId, request));
    }

    @Override
    public Result<OauthLicense> registerAndLogin(@Validated PasswordRequest request) {
        long accountId = accountService.verifyPasswordAccount(request);

        if (0 == accountId) {
            accountId = accountService.createPasswordAccount(request);
        }

        return Result.ok(createLicense(accountId, request));
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
