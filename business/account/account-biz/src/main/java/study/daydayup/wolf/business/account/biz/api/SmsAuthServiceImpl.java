package study.daydayup.wolf.business.account.biz.api;

import study.daydayup.wolf.business.account.api.dto.request.SmsCodeRequest;
import study.daydayup.wolf.business.account.api.dto.request.SmsRequest;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;
import study.daydayup.wolf.business.account.api.service.auth.SmsAuthService;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 6:02 下午
 **/
public class SmsAuthServiceImpl implements SmsAuthService {
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
        return null;
    }

    @Override
    public void sendCode(SmsCodeRequest request) {

    }
}
