package study.daydayup.wolf.demo.account.biz.authorization.entity.licensor;

import study.daydayup.wolf.demo.account.api.dto.LicenseDTO;
import study.daydayup.wolf.demo.account.api.dto.response.license.OAuth2ResponseDTO;
import study.daydayup.wolf.demo.account.api.dto.request.license.OAuth2LicenseRequest;
import study.daydayup.wolf.demo.account.api.enums.AuthorizationTypeEnum;
import study.daydayup.wolf.demo.account.api.exception.AuthorizationException;
import study.daydayup.wolf.demo.account.api.exception.OAuth2AccessTokenExpiredException;
import study.daydayup.wolf.demo.account.api.exception.OAuth2RefreshTokenExpiredException;
import study.daydayup.wolf.demo.account.biz.authorization.entity.Authentication;
import study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator.RefreshTokenAuthenticator;
import study.daydayup.wolf.demo.account.biz.authorization.repository.AuthorizationRepository;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.ClientVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Oauth2 implements Licensor<OAuth2LicenseRequest> {

    private AccessToken accessToken;

    private RefreshToken refreshToken;

    private AuthorizationRepository authorizationRepository;

    public Oauth2(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public OAuth2ResponseDTO grant(ClientVO clientVO, Authentication authentication) {
        if (authentication.getAuthorizationType().equals(AuthorizationTypeEnum.REFRESH_TOKEN.getType())) {
            refresh(clientVO, authentication);
        } else {
            create(clientVO, authentication);
        }

        OAuth2ResponseDTO auth2ResponseDTO = new OAuth2ResponseDTO();
        auth2ResponseDTO.setUid(authentication.getAccountVO().getUid());
        auth2ResponseDTO.setAccessToken(accessToken.getAccessToken());
        auth2ResponseDTO.setAccessTokenExpire(accessToken.getExpiredVO().getExpiredIn());
        auth2ResponseDTO.setNowTime((new Date()).getTime());
        auth2ResponseDTO.setRefreshToken(refreshToken.getRefreshToken());
        auth2ResponseDTO.setRefreshTokenExpire(refreshToken.getExpiredVO().getExpiredIn());
        return auth2ResponseDTO;
    }

    private void create(ClientVO clientVO, Authentication authentication) {
        createRefreshToken(authentication.getAccountVO(), clientVO);
        createAccessToken(authentication.getAccountVO(), clientVO);
    }

    private void refresh(ClientVO clientVO, Authentication authentication) {
        RefreshTokenAuthenticator refreshTokenAuthenticator = (RefreshTokenAuthenticator) authentication.getAuthenticator();
        refreshToken = refreshTokenAuthenticator.getRefreshToken();
        refreshToken.delayExpired();
        authorizationRepository.updateRefreshToken(refreshToken);
        createAccessToken(authentication.getAccountVO(), clientVO);
    }

    private void createAccessToken(AccountVO accountVO, ClientVO clientVO) {
        accessToken = new AccessToken(accountVO, clientVO, refreshToken, authorizationRepository);
        Boolean create = authorizationRepository.createAccessToken(accessToken);
        if (!create) {
            throw new AuthorizationException("创建授权令牌失败");
        }
    }

    private void createRefreshToken(AccountVO accountVO, ClientVO clientVO) {
        refreshToken = new RefreshToken(accountVO, clientVO, authorizationRepository);
        Boolean create = authorizationRepository.createRefreshToken(refreshToken);
        if (!create) {
            throw new AuthorizationException("创建刷新授权令牌失败");
        }
    }

    @Override
    public LicenseDTO authenticate(OAuth2LicenseRequest licenseRequest) {
        accessToken = authorizationRepository.getAccessToken(licenseRequest.getAccessToken());
        if (accessToken == null) {
            throw new OAuth2AccessTokenExpiredException("授权令牌错误");
        }

        if (!accessToken.getClientVO().getClientId().equals(licenseRequest.getClientId())) {
            throw new OAuth2AccessTokenExpiredException("授权令牌异常");
        }

        if (accessToken.isExpired()) {
            if (accessToken.isRefreshTokenExpired()) {
                throw new OAuth2RefreshTokenExpiredException("刷新令牌过期");
            }
            throw new OAuth2AccessTokenExpiredException("授权令牌过期");
        }

        LicenseDTO licenseDTO = new LicenseDTO();
        licenseDTO.setUid(accessToken.getAccountVO().getUid());
        return licenseDTO;
    }


}
