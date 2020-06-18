package study.daydayup.wolf.business.account.auth.agent.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.api.dto.request.SmsCodeRequest;
import study.daydayup.wolf.business.account.api.dto.request.SmsRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.auth.SmsAuthService;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.account.auth.agent.config.AuthConfig;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * study.daydayup.wolf.business.account.auth.agent.controller
 *
 * @author Wingle
 * @since 2019/12/4 5:44 下午
 **/
@RestController
public class SmsController extends AuthController {
    @Reference
    private SmsAuthService smsService;
    @Resource
    private Session session;
    @Resource
    private AuthConfig authConfig;

    @PostMapping("/auth/sms/login")
    public Result<OauthLicense> login(@Validated @RequestBody SmsRequest request) {
        return registerAndLogin(request);
    }

    @PostMapping("/auth/sms/registerAndLogin")
    public Result<OauthLicense> registerAndLogin(@Validated @RequestBody SmsRequest request) {
//        if (isLogin()) {
//            return Result.ok(getLicenseFromSession());
//        }

        request.setEnv(null);
        request.setToken(session.getSessionId());

        String scope = formatScope(request.getScope(), request.getOrgId());
        request.setScope(scope);
        request.setExpiredIn(authConfig.getExpiredIn());
        request.setRefreshExpiredIn(authConfig.getRefreshExpiredIn());

        Result<OauthLicense> result = smsService.registerAndLogin(request);
        OauthLicense license = result.notNullData();

        saveLicenseToSession(license);
        return Result.ok(filterLicense(license));
    }

    @PostMapping("/auth/sms/code")
    public Result<Object> code(@Validated @RequestBody SmsCodeRequest request) {
        request.setEnv(null);
        request.setExpiredIn(authConfig.getCodeExpiredIn());

        //TODO: 防刷
        smsService.sendCode(request);

        return Result.ok();
    }

    @PostMapping("/auth/sms/voice")
    public Result<Object> voice(@RequestBody SmsCodeRequest request) {
        return Result.ok();
    }
}
