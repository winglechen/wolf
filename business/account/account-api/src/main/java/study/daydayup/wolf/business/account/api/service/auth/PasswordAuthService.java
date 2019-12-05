package study.daydayup.wolf.business.account.api.service.auth;

import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;

/**
 * study.daydayup.wolf.business.account.api.service.auth
 *
 * @author Wingle
 * @since 2019/9/27 5:19 PM
 **/
public interface PasswordAuthService {
    long register(PasswordRequest request);

    OauthLicense login(PasswordRequest request);
    OauthLicense registerAndLogin(PasswordRequest request);
}
