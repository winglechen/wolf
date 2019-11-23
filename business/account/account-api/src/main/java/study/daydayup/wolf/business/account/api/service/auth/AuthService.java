package study.daydayup.wolf.business.account.api.service.auth;

import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.entity.License;

/**
 * study.daydayup.wolf.business.account.api.service.auth
 *
 * @author Wingle
 * @since 2019/9/27 5:37 PM
 **/
public interface AuthService {
    long register(Account account);
    long login(Account account);
    long registerAndLogin(Account account);
    void logout();
}
