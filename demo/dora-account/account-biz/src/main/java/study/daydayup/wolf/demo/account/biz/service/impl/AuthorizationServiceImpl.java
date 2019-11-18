package study.daydayup.wolf.demo.account.biz.service.impl;


import study.daydayup.wolf.demo.account.api.dto.ClientDTO;
import study.daydayup.wolf.demo.account.api.dto.request.authorization.*;
import study.daydayup.wolf.demo.account.api.dto.response.license.OAuth2ResponseDTO;
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

    private  <T extends AuthorizationRequest> OAuth2ResponseDTO oauth2(T authorizationRequest) {
        ClientDTO clientDTO = clientService.getClient(authorizationRequest.getClientId());
        if (clientDTO == null) {
            throw new ClientNonsupportException("不支持该登录客户端类型");
        }
        
        return (OAuth2ResponseDTO) authorizationDomainService.authorize(authorizationRequest, GrantTypeEnum.OAUTH2.getType());
    }


    @Override
    public OAuth2ResponseDTO alipayOauth2(@Valid AlipayAuthorizationRequest alipayAuthorizationRequest) {
        return oauth2(alipayAuthorizationRequest);
    }

    @Override
    public OAuth2ResponseDTO passwordOauth2(@Valid PasswordAuthorizationRequest passwordAuthorizationRequest) {
        return oauth2(passwordAuthorizationRequest);
    }

    @Override
    public OAuth2ResponseDTO refreshTokenOauth2(@Valid RefreshTokenAuthorizationRequest refreshTokenAuthorizationRequest) {
        return oauth2(refreshTokenAuthorizationRequest);
    }

    @Override
    public OAuth2ResponseDTO verifyCodeOauth2(@Valid VerifyCodeAuthorizationRequest verifyCodeAuthorizationRequest) {
        return oauth2(verifyCodeAuthorizationRequest);
    }

    @Override
    public OAuth2ResponseDTO wechatOauth2(@Valid WechatAuthorizationRequest wechatAuthorizationRequest) {
        return oauth2(wechatAuthorizationRequest);
    }
}
