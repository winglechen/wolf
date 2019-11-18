package study.daydayup.wolf.demo.account.api.service;

import study.daydayup.wolf.demo.account.api.dto.AccountWechatSessionKey;
import study.daydayup.wolf.demo.account.api.dto.request.account.AccountRequest;
import study.daydayup.wolf.demo.account.api.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("account")
@RequestMapping("account")
public interface AccountService {

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    Account create(@RequestBody AccountRequest accountRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/getByAccount")
    Account getByAccount(@RequestParam("account") String account);

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    Account getById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/getAccountWechatSessionKey")
    AccountWechatSessionKey getAccountWechatSessionKey(@RequestParam("id") Long id);
}
