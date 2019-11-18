package study.daydayup.wolf.demo.account.biz.authorization.service;

import study.daydayup.wolf.demo.account.api.dto.LicenseDTO;
import study.daydayup.wolf.demo.account.api.dto.request.auth.AuthRequest;
import study.daydayup.wolf.demo.account.api.dto.request.license.LicenseRequest;
import study.daydayup.wolf.demo.account.api.exception.AuthorizationException;
import study.daydayup.wolf.demo.account.biz.authorization.entity.Authentication;
import study.daydayup.wolf.demo.account.biz.authorization.entity.License;
import study.daydayup.wolf.demo.account.biz.authorization.factory.AuthorizationFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthorizationDomainService {

    @Resource
    private AuthorizationFactory authorizationFactory;

    public <T extends AuthRequest> LicenseDTO authorize(T authorizationRequest, Integer grantType) {
        Authentication authentication = authorizationFactory.registerAuthentication(authorizationRequest.getAuthorizationType());
        authentication.authenticate(authorizationRequest);
        if (!authentication.isAuthentic()) {
            throw new AuthorizationException("授权失败，请稍后重试");
        }

        License license = authorizationFactory.registerLicense(authorizationRequest.getClientId(), grantType);
        return license.grant(authentication);
    }

    public <T extends LicenseRequest> LicenseDTO authenticate(T licenseRequest) {
        License license = authorizationFactory.registerLicense(licenseRequest.getClientId(), licenseRequest.getGrantType());
        return license.authenticate(licenseRequest);
    }
}
