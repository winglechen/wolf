package study.daydayup.wolf.business.account.biz.api;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.api.service.auth.PasswordAuthService;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 6:01 下午
 **/
public class PasswordAuthServiceImpl implements PasswordAuthService {
    @Resource
    private AccountService accountService;
    @Resource
    private OauthLicenseService licenseService;

    @Override
    public long register(PasswordRequest request) {
        return 0;
    }

    @Override
    public OauthLicense login(PasswordRequest request) {
        return null;
    }

    @Override
    public OauthLicense registerAndLogin(PasswordRequest request) {
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

        return licenseService.grant(licenseRequest);
    }
}
