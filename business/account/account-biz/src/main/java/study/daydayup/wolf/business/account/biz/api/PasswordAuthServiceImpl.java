package study.daydayup.wolf.business.account.biz.api;

import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.auth.PasswordAuthService;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 6:01 下午
 **/
public class PasswordAuthServiceImpl implements PasswordAuthService {
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
        return null;
    }
}
