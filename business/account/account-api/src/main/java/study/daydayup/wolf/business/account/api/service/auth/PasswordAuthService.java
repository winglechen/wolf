package study.daydayup.wolf.business.account.api.service.auth;

import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.account.api.service.auth
 *
 * @author Wingle
 * @since 2019/9/27 5:19 PM
 **/
public interface PasswordAuthService {
    Result<Long> register(PasswordRequest request);
    Result<Object> changePassword(PasswordRequest request);

    Result<OauthLicense> login(PasswordRequest request);
    Result<OauthLicense> registerAndLogin(PasswordRequest request);
}
