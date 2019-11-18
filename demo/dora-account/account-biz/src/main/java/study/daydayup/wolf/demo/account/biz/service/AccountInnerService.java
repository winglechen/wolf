package study.daydayup.wolf.demo.account.biz.service;

import study.daydayup.wolf.demo.account.api.dto.request.account.AccountRequest;
import study.daydayup.wolf.demo.account.api.model.Account;

public interface AccountInnerService {

    Boolean changeAccountById(Long id, String account);

    Account create(AccountRequest accountRequest);

    Account getByAccount(String account);

    Account getById(Long id);

    Account getWithCreateAccount(String mobile);

}
