package study.daydayup.wolf.business.account.api.service;

import study.daydayup.wolf.business.account.api.entity.Account;

/**
 * study.daydayup.wolf.business.account.api.service
 * TODO: add license operations
 * @author Wingle
 * @since 2019/9/27 5:21 PM
 **/
public interface AccountService {
    long create(Account account);
    long existByAccount(String account);
    Account findByAccount(String account);
    Account findById(long id);

}
