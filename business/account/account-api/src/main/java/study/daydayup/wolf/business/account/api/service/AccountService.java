package study.daydayup.wolf.business.account.api.service;

import study.daydayup.wolf.business.account.api.entity.Account;

/**
 * study.daydayup.wolf.business.account.api.service
 *
 * @author Wingle
 * @since 2019/9/27 5:21 PM
 **/
public interface AccountService {
    long /* account id */ create(Account account);
    Account findByAccount(String account);
    Account findById(long id);
}
