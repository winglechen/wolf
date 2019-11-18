package study.daydayup.wolf.demo.account.biz.authorization.entity.licensor;

import study.daydayup.wolf.demo.account.api.exception.AuthorizationException;
import study.daydayup.wolf.demo.account.api.exception.OAuth2RefreshTokenExpiredException;
import study.daydayup.wolf.demo.account.biz.authorization.repository.AuthorizationRepository;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.ClientVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.DateExpiredVO;
import study.daydayup.wolf.common.util.encrypt.ShaEncrypt;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Setter
@Getter
public class AccessToken {

    private String accessToken;

    private AccountVO accountVO;

    private DateExpiredVO expiredVO;

    private ClientVO clientVO;

    private RefreshToken refreshToken;

    private AuthorizationRepository authorizationRepository;

    public AccessToken(AccountVO accountVO, ClientVO clientVO, RefreshToken refreshToken, AuthorizationRepository authorizationRepository) {
        this.accountVO = accountVO;
        this.clientVO = clientVO;
        this.authorizationRepository = authorizationRepository;
        this.refreshToken = refreshToken;

        init();
    }

    public AccessToken(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    private void init() {
        generateAccessToken();
        generateExpired();
    }

    private void generateAccessToken() {
        Random rand = new Random();
        UUID uuid = UUID.randomUUID();
        String uniqueStr = String.valueOf(rand.nextInt()) + String.valueOf(rand.nextInt()) + String.valueOf(rand.nextInt()) + String.valueOf(rand.nextInt()) + String.valueOf(new Date().getTime())+uuid.toString();
        try {
            accessToken = ShaEncrypt.sha512(uniqueStr).substring(0, 30);
        } catch (Exception e) {
            throw new AuthorizationException("生成授权令牌授权码失败");
        }
    }

    private void generateExpired() {
        expiredVO = new DateExpiredVO(DateUtils.addSeconds(new Date(), 60 * 60 * 24));
    }

    public boolean isExpired() {
        return getExpiredVO().isExpired();
    }

    public boolean isRefreshTokenExpired() {
        if (getRefreshToken() == null) {
            throw new OAuth2RefreshTokenExpiredException("刷新令牌错误");
        }
        return getRefreshToken().isExpired();
    }
}
