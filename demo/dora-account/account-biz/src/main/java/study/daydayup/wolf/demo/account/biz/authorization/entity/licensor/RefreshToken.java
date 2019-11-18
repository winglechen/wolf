package study.daydayup.wolf.demo.account.biz.authorization.entity.licensor;

import study.daydayup.wolf.demo.account.api.exception.AuthorizationException;
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
public class RefreshToken {

    private String refreshToken;

    private AccountVO accountVO;

    private DateExpiredVO expiredVO;

    private ClientVO clientVO;

    private AuthorizationRepository authorizationRepository;

    public RefreshToken(AccountVO accountVO, ClientVO clientVO, AuthorizationRepository authorizationRepository) {
        this.accountVO = accountVO;
        this.clientVO = clientVO;
        this.authorizationRepository = authorizationRepository;
        init();
    }

    public RefreshToken(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    private void init() {
        generateRefreshToken();
        generateExpired();
    }

    private void generateRefreshToken() {
        Random rand = new Random();
        UUID uuid = UUID.randomUUID();
        String uniqueStr = String.valueOf(rand.nextInt()) + String.valueOf(rand.nextInt()) + String.valueOf(rand.nextInt()) + String.valueOf(rand.nextInt()) + String.valueOf(new Date().getTime())+uuid.toString();
        try {
            refreshToken = ShaEncrypt.sha512(uniqueStr).substring(0, 30);
        } catch (Exception e) {
            throw new AuthorizationException("生成授权令牌授权失败");
        }
    }

    private void generateExpired() {
        expiredVO = new DateExpiredVO(DateUtils.addSeconds(new Date(), 60 * 60 * 24 * 10));
    }

    public boolean isExpired() {
        return getExpiredVO().isExpired();
    }

    public void delayExpired() {
        generateExpired();
    }
}
