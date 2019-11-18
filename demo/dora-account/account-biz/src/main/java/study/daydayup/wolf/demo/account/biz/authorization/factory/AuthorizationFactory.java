package study.daydayup.wolf.demo.account.biz.authorization.factory;

import study.daydayup.wolf.demo.account.biz.authorization.entity.Authentication;
import study.daydayup.wolf.demo.account.biz.authorization.entity.License;
import study.daydayup.wolf.demo.account.biz.authorization.repository.AuthorizationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthorizationFactory {

    @Resource
    private AuthorizationRepository authorizationRepository;

    public Authentication registerAuthentication(Integer authorizationType) {
        return new Authentication(authorizationType, authorizationRepository);
    }

    public License registerLicense(String clientId, Integer grantType) {
        return new License(clientId, grantType, authorizationRepository);
    }













}
