package study.daydayup.wolf.business.account.api.service.auth;

import study.daydayup.wolf.business.account.api.entity.Account;

/**
 * study.daydayup.wolf.business.account.api.service.auth
 *
 * @author Wingle
 * @since 2019/9/27 5:19 PM
 **/
public interface SmsAuthService extends AuthService{
    void sendVerifyCode();
    /**
     * login, register
     * @object AccountType
     * @param {Account<mobile phone>}
     */

}
