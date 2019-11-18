package study.daydayup.wolf.demo.account.api.service;

import study.daydayup.wolf.demo.account.api.dto.request.VerifyCodeSendRequest;
import study.daydayup.wolf.demo.account.api.dto.response.VerifyCodeSendResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient("account")
@RequestMapping("verifyCode")
public interface VerifyCodeService {

    @RequestMapping(method = RequestMethod.POST, value = "/send")
    VerifyCodeSendResponse send(@RequestBody @Valid VerifyCodeSendRequest verifyCodeSendRequest);
}
