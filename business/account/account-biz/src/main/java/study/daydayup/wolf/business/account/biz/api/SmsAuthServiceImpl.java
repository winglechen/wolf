package study.daydayup.wolf.business.account.biz.api;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.account.api.dto.request.LicenseRequest;
import study.daydayup.wolf.business.account.api.dto.request.SmsCodeRequest;
import study.daydayup.wolf.business.account.api.dto.request.SmsRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.api.service.auth.SmsAuthService;
import study.daydayup.wolf.business.account.api.service.licenser.OauthLicenseService;
import study.daydayup.wolf.business.account.biz.service.VerifyCodeService;
import study.daydayup.wolf.common.util.DateUtil;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.util.Date;

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
    public long register(SmsRequest request) {
        return 0;
    }

    @Override
    public OauthLicense login(SmsRequest request) {
        return null;
    }

    @Override
    public OauthLicense registerAndLogin(SmsRequest request) {
        //verify the code
        if (!verifyCodeService.verify(request.getMobile(), request.getCode())) {
            System.out.println("invalid code:" + request.getCode());
            return null;
        }

        //checkAccountExist
        long accountId = accountService.existByAccount(request.getMobile());

        //create account if needed
        if (0 == accountId) {
            accountId = accountService.createSmsAccount(request.getMobile(), request.getSource());
        }

        //grant the license
        LicenseRequest licenseRequest = new LicenseRequest();
        BeanUtils.copyProperties(request, licenseRequest);
        licenseRequest.setAccountId(accountId);

        return licenseService.grant(licenseRequest);
    }

    @Override
    public void sendCode(SmsCodeRequest request) {
        String mobile = request.getMobile();
        Date expiredAt = DateUtil.secondsLater(request.getExpiredIn());

        verifyCodeService.send(mobile, createCode(), expiredAt);
    }

    private String createCode() {
        return "123";
        //TODO remove the mock data
//        int num = ThreadLocalRandom.current().nextInt(100000, 999999);
//        return Integer.toString(num);
    }
}
