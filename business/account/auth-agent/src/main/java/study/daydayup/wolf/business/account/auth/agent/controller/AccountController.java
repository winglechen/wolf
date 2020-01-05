package study.daydayup.wolf.business.account.auth.agent.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.common.util.DateUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * study.daydayup.wolf.demo.ali.consumer
 *
 * @author Wingle
 * @since 2019/10/11 10:23 上午
 **/
@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Reference
    private AccountService accountService;
    @Resource
    private Session session;

    @RequestMapping("/account/create")
    public String create() {
        Account a = new Account();
        a.setAccount("wingle");

        long result = accountService.create(a);

        return  "generic result: " + result;
    }

    @RequestMapping("/account/show")
    public String show() {
        Long accountId = (Long) session.get("accountId");
        Long orgId = (Long)session.get("orgId");
        LocalDateTime expiredAt = DateUtil.asLocalDateTime((Date)session.get("expiredAt"));
        LocalDateTime now = LocalDateTime.now();
        Date dNow = new Date();

        return "accountId:" + accountId + "; orgId:" + orgId
                + "\nexpiredAt:" + expiredAt
                + "\nnow:" + now
                + "\ndateNow:" + DateUtil.asLocalDateTime(dNow);
    }


}
