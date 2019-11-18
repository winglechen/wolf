package study.daydayup.wolf.demo.account.api.service;

import study.daydayup.wolf.demo.account.api.dto.request.authorization.*;
import study.daydayup.wolf.demo.account.api.dto.response.license.OAuth2ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient("account")
@RequestMapping("authorization")
public interface AuthorizationService  {

    @RequestMapping(method = RequestMethod.POST, value = "/alipayOauth2")
    OAuth2ResponseDTO alipayOauth2(@RequestBody @Valid AlipayAuthorizationRequest alipayAuthorizationRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/passwordOauth2")
    OAuth2ResponseDTO passwordOauth2(@RequestBody @Valid PasswordAuthorizationRequest passwordAuthorizationRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/refreshTokenOauth2")
    OAuth2ResponseDTO refreshTokenOauth2(@RequestBody @Valid RefreshTokenAuthorizationRequest refreshTokenAuthorizationRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/verifyCodeOauth2")
    OAuth2ResponseDTO verifyCodeOauth2(@RequestBody @Valid VerifyCodeAuthorizationRequest verifyCodeAuthorizationRequest);

    @RequestMapping(method = RequestMethod.POST, value = "/wechatAppOauth2")
    OAuth2ResponseDTO wechatOauth2(@RequestBody @Valid WechatAuthorizationRequest wechatAuthorizationRequest);

}
