package study.daydayup.wolf.business.account.biz.api;

import lombok.extern.slf4j.Slf4j;
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
import study.daydayup.wolf.common.util.time.DateUtil;
import study.daydayup.wolf.framework.exception.LocaleNotFoundException;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.util.LocaleUtil;
import study.daydayup.wolf.middleware.notice.agent.service.SMSAgent;

import javax.annotation.Resource;
import java.util.Date;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 6:02 下午
 **/
@Slf4j
@RpcService(protocol = "dubbo")
public class SmsAuthServiceImpl implements SmsAuthService {
    private static final String OTP_KEY = "account.login.otp";
    private static final String TEST_OTP = "123";

    @Resource
    private AccountService accountService;
    @Reference
    private OauthLicenseService licenseService;
    @Resource
    private VerifyCodeService verifyCodeService;
    @Resource
    private SMSAgent smsAgent;

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
    public Result<Object> sendCode(SmsCodeRequest request) {
        String mobile = request.getMobile();
        String code = createCode();
        Date expiredAt = DateUtil.secondsLater(request.getExpiredIn());

        verifyCodeService.send(mobile, code, expiredAt);
        sendSms(mobile, code);

        return Result.ok();
    }

    private void sendSms(String mobile, String code) {
        if (TEST_OTP.equals(code)) {
//            return;
        }

        String[] args = new String[]{code};
        String msg = LocaleUtil.get(OTP_KEY, args);
        if (msg == null) {
            throw new LocaleNotFoundException(OTP_KEY);
        }

        smsAgent.toIndia(mobile, msg);
    }

    private String createCode() {
        return TEST_OTP;
        //TODO remove the mock data
//        int num = ThreadLocalRandom.current().nextInt(100000, 999999);
//        return Integer.toString(num);
    }
}
