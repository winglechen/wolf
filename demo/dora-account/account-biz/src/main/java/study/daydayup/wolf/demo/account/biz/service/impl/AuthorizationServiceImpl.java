package study.daydayup.wolf.demo.account.biz.service.impl;


import study.daydayup.wolf.demo.account.api.dto.ClientDTO;
import study.daydayup.wolf.demo.account.api.dto.request.auth.*;
import study.daydayup.wolf.demo.account.api.dto.response.license.OAuth2Response;
import study.daydayup.wolf.demo.account.api.enums.GrantTypeEnum;
import study.daydayup.wolf.demo.account.api.exception.ClientNonsupportException;
import study.daydayup.wolf.demo.account.api.service.AuthorizationService;
import study.daydayup.wolf.demo.account.biz.authorization.service.AuthorizationDomainService;
import study.daydayup.wolf.demo.account.biz.service.ClientService;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import javax.validation.Valid;

@RpcService
public class AuthorizationServiceImpl implements AuthorizationService {

    @Resource
    private AuthorizationDomainService authorizationDomainService;
    
    @Resource
    private ClientService clientService;

    private  <T extends AuthRequest> OAuth2Response oauth2(T authorizationRequest) {
        ClientDTO clientDTO = clientService.getClient(authorizationRequest.getClientId());
        if (clientDTO == null) {
            throw new ClientNonsupportException("不支持该登录客户端类型");
        }
        
        return (OAuth2Response) authorizationDomainService.authorize(authorizationRequest, GrantTypeEnum.OAUTH2.getType());
    }


    @Override
    public OAuth2Response alipayOauth2(@Valid AlipayRequest alipayRequest) {
        return oauth2(alipayRequest);
    }

    @Override
    public OAuth2Response passwordOauth2(@Valid PasswordRequest passwordAuthorizationRequest) {
        return oauth2(passwordAuthorizationRequest);
    }

    @Override
    public OAuth2Response refreshTokenOauth2(@Valid RefreshTokenRequest refreshTokenAuthorizationRequest) {
        return oauth2(refreshTokenAuthorizationRequest);
    }

    @Override
    public OAuth2Response verifyCodeOauth2(@Valid VerifyCodeRequest verifyCodeAuthorizationRequest) {
        return oauth2(verifyCodeAuthorizationRequest);
    }

    @Override
    public OAuth2Response wechatOauth2(@Valid WechatRequest wechatAuthorizationRequest) {
        return oauth2(wechatAuthorizationRequest);
    }
}
