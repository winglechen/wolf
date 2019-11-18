package study.daydayup.wolf.demo.account.biz.service.impl;

import study.daydayup.wolf.demo.account.api.dto.ClientDTO;
import study.daydayup.wolf.demo.account.api.dto.LicenseDTO;
import study.daydayup.wolf.demo.account.api.dto.request.license.LicenseRequest;
import study.daydayup.wolf.demo.account.api.dto.request.license.OAuth2LicenseRequest;
import study.daydayup.wolf.demo.account.api.exception.ClientNonsupportException;
import study.daydayup.wolf.demo.account.api.service.LicenseService;
import study.daydayup.wolf.demo.account.biz.authorization.service.AuthorizationDomainService;
import study.daydayup.wolf.demo.account.biz.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import javax.validation.Valid;

@RpcService
@Slf4j
public class LicenseServiceImpl implements LicenseService {

    @Resource
    private ClientService clientService;

    @Resource
    private AuthorizationDomainService authorizationDomainService;

    private  <T extends LicenseRequest> LicenseDTO authenticate(T licenseRequest) {
        ClientDTO clientDTO = clientService.getClient(licenseRequest.getClientId());
        if (clientDTO == null) {
            throw new ClientNonsupportException("不支持该登录客户端类型");
        }

        return authorizationDomainService.authenticate(licenseRequest);
    }

    @Override
    public LicenseDTO oauth2Authenticate(@Valid OAuth2LicenseRequest licenseRequest) {
        return authenticate(licenseRequest);
    }
}
