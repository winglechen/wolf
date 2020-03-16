package study.daydayup.wolf.business.account.auth.agent.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.auth.PasswordAuthService;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.account.auth.agent.config.AuthConfig;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * study.daydayup.wolf.business.account.auth.agent.controller
 *
 * @author Wingle
 * @since 2019/12/4 5:44 下午
 **/
@RestController
public class PasswordController extends AuthController {
    @Reference
    private PasswordAuthService passwordService;
    @Resource
    private AuthConfig authConfig;
    @Resource
    private Session session;

    @PostMapping("/auth/password/login")
    public Result<OauthLicense> login(HttpServletRequest httpRequest, @Valid @RequestBody PasswordRequest passwordRequest) {
//        if(isLogin()) {
//            return Result.ok(getLicenseFromSession());
//        }

        if (!CaptchaUtil.ver(passwordRequest.getCaptcha(), httpRequest)) {
            CaptchaUtil.clear(httpRequest);
            return Result.fail(10000, "invalid captcha");
        }

        passwordRequest.setEnv(null);
        passwordRequest.setToken(session.getSessionId());

        String scope = formatScope(passwordRequest.getScope(), passwordRequest.getOrgId());
        passwordRequest.setScope(scope);
        passwordRequest.setExpiredIn(authConfig.getExpiredIn());
        passwordRequest.setRefreshExpiredIn(authConfig.getRefreshExpiredIn());

        Result<OauthLicense> result = passwordService.login(passwordRequest);
        OauthLicense license = result.notNullData();
        saveLicenseToSession(license);

        return Result.ok(filterLicense(license));
    }

    @PostMapping("/auth/password/registerAndLogin")
    public Result<OauthLicense> registerAndLogin(@Valid @RequestBody PasswordRequest request) {
//        if(isLogin()) {
//            return Result.ok(getLicenseFromSession());
//        }

        request.setEnv(null);
        request.setToken(session.getSessionId());
        request.setExpiredIn(authConfig.getExpiredIn());
        request.setRefreshExpiredIn(authConfig.getRefreshExpiredIn());

        String scope = formatScope(request.getScope(), request.getOrgId());
        request.setScope(scope);

        Result<OauthLicense> result = passwordService.registerAndLogin(request);
        OauthLicense license = result.notNullData();
        saveLicenseToSession(license);

        return Result.ok(filterLicense(license));
    }

    @PostMapping("/auth/password/register")
    public Result register(@Validated @RequestBody PasswordRequest request) {
        request.setEnv(null);
        passwordService.register(request);

        return Result.ok();
    }

    @PostMapping("/auth/password/change")
    public Result changePassword(@Validated @RequestBody PasswordRequest request) {
        request.setEnv(null);
        passwordService.changePassword(request);

        return Result.ok();
    }

}
