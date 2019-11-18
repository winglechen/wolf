package study.daydayup.wolf.demo.account.api.service;

import study.daydayup.wolf.demo.account.api.dto.request.auth.*;
import study.daydayup.wolf.demo.account.api.dto.response.license.OAuth2Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient("account")
@RequestMapping("authorization")
public interface AuthorizationService  {

    @RequestMapping(method = RequestMethod.POST, value = "/alipayOauth2")
    OAuth2Response alipayOauth2(@RequestBody @Valid AlipayRequest alipayRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/passwordOauth2")
    OAuth2Response passwordOauth2(@RequestBody @Valid PasswordRequest passwordAuthorizationRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/refreshTokenOauth2")
    OAuth2Response refreshTokenOauth2(@RequestBody @Valid RefreshTokenRequest refreshTokenAuthorizationRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/verifyCodeOauth2")
    OAuth2Response verifyCodeOauth2(@RequestBody @Valid VerifyCodeRequest verifyCodeAuthorizationRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/wechatAppOauth2")
    OAuth2Response wechatOauth2(@RequestBody @Valid WechatRequest wechatAuthorizationRequest);

}
