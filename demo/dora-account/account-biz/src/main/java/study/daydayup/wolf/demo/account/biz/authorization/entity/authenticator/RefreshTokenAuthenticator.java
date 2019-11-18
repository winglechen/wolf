package study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator;

import study.daydayup.wolf.demo.account.api.dto.request.authorization.RefreshTokenAuthorizationRequest;
import study.daydayup.wolf.demo.account.api.exception.OAuth2RefreshTokenExpiredException;
import study.daydayup.wolf.demo.account.biz.authorization.entity.licensor.RefreshToken;
import study.daydayup.wolf.demo.account.biz.authorization.repository.AuthorizationRepository;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class RefreshTokenAuthenticator implements Authenticator<RefreshTokenAuthorizationRequest> {

    private AuthorizationRepository authorizationRepository;

    private RefreshToken refreshToken;

    public RefreshTokenAuthenticator(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public AccountVO authenticate(RefreshTokenAuthorizationRequest request) {
        refreshToken = authorizationRepository.getRefreshToken(request.getRefreshToken());
        if (refreshToken == null) {
            throw new OAuth2RefreshTokenExpiredException("刷新令牌错误");
        }
        if (!request.getClientId().equals(refreshToken.getClientVO().getClientId())) {
            throw new OAuth2RefreshTokenExpiredException("刷新令牌异常");
        }
        if (refreshToken.isExpired()) {
            throw new OAuth2RefreshTokenExpiredException("刷新令牌过期");
        }

        return refreshToken.getAccountVO();
    }
}
