package study.daydayup.wolf.business.account.biz.api;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.dto.request.SmsCodeRequest;
import study.daydayup.wolf.business.account.api.dto.request.SmsRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.exception.InvalidVerifyCodeException;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.api.service.auth.SmsAuthService;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.biz.service.VerifyCodeService;
import study.daydayup.wolf.common.util.DateUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 6:02 下午
 **/
@RpcService(protocol = "dubbo")
public class SmsAuthServiceImpl implements SmsAuthService {
    @Resource
    private AccountService accountService;
    @Reference
    private OauthLicenseService licenseService;
    @Resource
    private VerifyCodeService verifyCodeService;

    @Override
    public Result<Long> register(SmsRequest request) {
        return Result.ok(0L);
    }

    @Override
    public Result<OauthLicense> login(SmsRequest request) {
        return Result.ok(null);
    }

    @Override
    public Result<OauthLicense> registerAndLogin(SmsRequest request) {
        if (!verifyCodeService.verify(request.getMobile(), request.getCode())) {
            throw new InvalidVerifyCodeException();
        }

        long accountId = accountService.existByAccount(request.getMobile());
        if (0 == accountId) {
            accountId = accountService.createSmsAccount(request.getMobile(), request.getSource());
        }

        LicenseRequest licenseRequest = new LicenseRequest();
        BeanUtils.copyProperties(request, licenseRequest);
        licenseRequest.setAccountId(accountId);

        OauthLicense license = licenseService.grant(licenseRequest);
        return Result.ok(license);
    }

    @Override
    public Result sendCode(SmsCodeRequest request) {
        String mobile = request.getMobile();
        Date expiredAt = DateUtil.secondsLater(request.getExpiredIn());

        verifyCodeService.send(mobile, createCode(), expiredAt);
        return Result.ok();
    }

    private String createCode() {
        return "123";
        //TODO remove the mock data
//        int num = ThreadLocalRandom.current().nextInt(100000, 999999);
//        return Integer.toString(num);
    }
}
