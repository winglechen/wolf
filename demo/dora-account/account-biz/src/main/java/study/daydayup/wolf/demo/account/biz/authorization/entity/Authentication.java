package study.daydayup.wolf.demo.account.biz.authorization.entity;

import study.daydayup.wolf.demo.account.api.dto.request.authorization.AuthorizationRequest;
import study.daydayup.wolf.demo.account.api.enums.AuthorizationTypeEnum;
import study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator.*;
import study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator.*;
import study.daydayup.wolf.demo.account.biz.authorization.repository.AuthorizationRepository;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authentication {

    private AccountVO accountVO;

    private boolean isAuthentic;

    private Integer authorizationType;

    private Authenticator authenticator;

    private AuthorizationRepository authorizationRepository;

    public Authentication(Integer authorizationType, AuthorizationRepository authorizationRepository) {
        this.authorizationType = authorizationType;
        this.authorizationRepository = authorizationRepository;
        this.isAuthentic = false;
        initAuthenticator();
    }

    private void initAuthenticator() {
        AuthorizationTypeEnum authorizationTypeEnum = AuthorizationTypeEnum.getAuthorizationTypeEnumByType(authorizationType);
        switch (authorizationTypeEnum) {
            case PASSWORD:
                break;

            case WECHAT_APP:
            case WECHAT_MP:
                authenticator = new WechatAuthenticator(authorizationRepository);
                break;
            case WECHAT_MINI:
                authenticator  = new WechatMiniAuthenticator(authorizationRepository);
                break;

            case ALIPAY:
            case ALIPAY_LIVE:
                break;

            case VERIFY_CODE:
                authenticator = new VerifyCodeAuthenticator(authorizationRepository);
                break;

            case REFRESH_TOKEN:
                authenticator = new RefreshTokenAuthenticator(authorizationRepository);
                break;
        }
    }




    public <T extends AuthorizationRequest> void authenticate(T authorizationRequest) {
        accountVO = authenticator.authenticate(authorizationRequest);
        this.isAuthentic = accountVO.isValid();
    }
}
