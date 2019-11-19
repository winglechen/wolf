package study.daydayup.wolf.business.account.api.service.auth;

import study.daydayup.wolf.business.account.api.entity.License;
import study.daydayup.wolf.business.account.api.entity.account.AuthAccount;

/**
 * study.daydayup.wolf.business.account.api.service.auth
 *
 * @author Wingle
 * @since 2019/9/27 5:37 PM
 **/
public interface AuthService {
    long /* accountId*/ register(AuthAccount auth);
    License login();
    boolean logout();
}
