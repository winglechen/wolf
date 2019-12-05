package study.daydayup.wolf.business.account.auth.agent.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.auth.PasswordAuthService;
import study.daydayup.wolf.framework.rpc.Result;

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

    @GetMapping("/auth/password/login")
    public Result login(@Valid PasswordRequest request) {
        return Result.ok();
    }

    public Result registerAndLogin(@Valid PasswordRequest request) {
        if(isLogin()) {
            return Result.ok();
        }

        request.setEnv(null);

        OauthLicense license = passwordService.registerAndLogin(request);
        saveLicenseToSession(license);

        return Result.ok();
    }
}
