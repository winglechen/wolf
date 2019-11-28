package study.daydayup.wolf.business.account.deploy.dubbo.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.service.AccountService;

/**
 * study.daydayup.wolf.demo.ali.consumer
 *
 * @author Wingle
 * @since 2019/10/11 10:23 上午
 **/
@RestController
public class AccountController {
    @Reference
    private AccountService accountService;


    @RequestMapping("/account/create")
    public String create() {
        Account a = new Account();
        a.setAccount("wingle");

        System.out.println("Account: " + a);
        long result = accountService.create(a);
        System.out.println("result:" + result);

        return  "generic result: " + result;
    }


}
