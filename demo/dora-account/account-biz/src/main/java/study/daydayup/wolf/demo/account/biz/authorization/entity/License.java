package study.daydayup.wolf.demo.account.biz.authorization.entity;

import study.daydayup.wolf.demo.account.api.dto.LicenseDTO;
import study.daydayup.wolf.demo.account.api.dto.request.license.LicenseRequest;
import study.daydayup.wolf.demo.account.api.enums.GrantTypeEnum;
import study.daydayup.wolf.demo.account.api.exception.AuthorizationException;
import study.daydayup.wolf.demo.account.biz.authorization.entity.licensor.Licensor;
import study.daydayup.wolf.demo.account.biz.authorization.entity.licensor.Oauth2;
import study.daydayup.wolf.demo.account.biz.authorization.repository.AuthorizationRepository;
import study.daydayup.wolf.demo.account.biz.authorization.vo.ClientVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class License {

    private Licensor licensor;

    private Integer grantType;

    private String clientId;

    private ClientVO clientVO;

    private AuthorizationRepository authorizationRepository;

    public License(String clientId, Integer grantType, AuthorizationRepository authorizationRepository) {
        this.clientId = clientId;
        this.grantType = grantType;
        this.authorizationRepository = authorizationRepository;

        initClient();
        initLicensor();
    }

    private void initLicensor() {
        GrantTypeEnum grantTypeEnum = GrantTypeEnum.geGrantTypeEnumByType(grantType);
        switch (grantTypeEnum) {
            case OAUTH2:
                licensor = new Oauth2(authorizationRepository);
                break;
        }
    }

    private void initClient() {
        ClientVO clientVO = authorizationRepository.getClient(clientId);
        if (clientVO == null) {
            throw new AuthorizationException("授权客户端错误，请确认重试");
        }
        this.clientVO = clientVO;
    }

    public LicenseDTO grant(Authentication authentication) {
        return licensor.grant(getClientVO(), authentication);
    }

    public <T extends LicenseRequest> LicenseDTO authenticate(T licenseRequest) {
        return licensor.authenticate(licenseRequest);
    }
























}
