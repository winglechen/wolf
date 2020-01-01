package study.daydayup.wolf.business.account.biz.api;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
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
    public Result<Long> register(PasswordRequest request) {
        return Result.ok(0L);
    }

    @Override
    public Result changePassword(PasswordRequest request) {

        return Result.ok();
    }

    @Override
    public Result<OauthLicense> login(PasswordRequest request) {
        return Result.ok(null);
    }

    @Override
    public Result<OauthLicense> registerAndLogin(PasswordRequest request) {
        //checkAccountExist
        long accountId = accountService.existByAccount(request.getAccount());

        //create account if needed
        if (0 == accountId) {
            accountId = accountService.createPasswordAccount(request);
        }

        //grant the license
        LicenseRequest licenseRequest = new LicenseRequest();
        BeanUtils.copyProperties(request, licenseRequest);
        licenseRequest.setAccountId(accountId);

        OauthLicense license = licenseService.grant(licenseRequest);
        return Result.ok(license);
    }
}
