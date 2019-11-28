package study.daydayup.wolf.business.account.api.agent;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.service.AccountService;

/**
 * study.daydayup.wolf.business.account.api.agent
 *
 * @author Wingle
 * @since 2019/11/28 9:47 上午
 **/
@Component
public class AccountAgent {
    @Reference
    private AccountService accountService;

    public long create() {
        Account a = new Account();
        a.setAccount("chen");

        return accountService.create(a);
    }
}
