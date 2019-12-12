package study.daydayup.wolf.business.account.api.service.auth;

import study.daydayup.wolf.business.account.api.dto.request.SmsCodeRequest;
import study.daydayup.wolf.business.account.api.dto.request.SmsRequest;
import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.entity.license.OauthLicense;

/**
 * study.daydayup.wolf.business.account.api.service.auth
 *
 * @author Wingle
 * @since 2019/9/27 5:19 PM
 **/
//TODO return Result<?>
public interface SmsAuthService {
    long register(SmsRequest request);
    OauthLicense login(SmsRequest request);

    OauthLicense registerAndLogin(SmsRequest request);

    void sendCode(SmsCodeRequest request);


}
