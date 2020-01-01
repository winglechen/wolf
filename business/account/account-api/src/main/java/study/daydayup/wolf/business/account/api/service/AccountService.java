package study.daydayup.wolf.business.account.api.service;

import study.daydayup.wolf.business.account.api.dto.request.PasswordRequest;
import study.daydayup.wolf.business.account.api.entity.Account;

/**
 * study.daydayup.wolf.business.account.api.service
 *
 * @author Wingle
 * @since 2019/9/27 5:21 PM
 **/
public interface AccountService {
    long create(Account account);
    long createSmsAccount(String mobile, String source);
    long createPasswordAccount(PasswordRequest request);
    long verifyPasswordAccount(PasswordRequest request);

    void changePassword(PasswordRequest request);

    long existByAccount(String account);
    Account findByAccount(String account);
    Account findById(long id);


}
