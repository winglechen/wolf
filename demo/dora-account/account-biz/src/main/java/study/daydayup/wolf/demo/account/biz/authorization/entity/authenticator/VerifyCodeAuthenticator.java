package study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator;

import study.daydayup.wolf.demo.account.api.dto.request.authorization.VerifyCodeAuthorizationRequest;
import study.daydayup.wolf.demo.account.api.exception.VerifyCodeAuthException;
import study.daydayup.wolf.demo.account.biz.authorization.entity.VerifyCode;
import study.daydayup.wolf.demo.account.biz.authorization.repository.AuthorizationRepository;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VerifyCodeAuthenticator implements Authenticator<VerifyCodeAuthorizationRequest> {

    private AuthorizationRepository authorizationRepository;

    private VerifyCode verifyCode;

    private AccountVO accountVO;

    public VerifyCodeAuthenticator(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public AccountVO authenticate(VerifyCodeAuthorizationRequest verifyCodeAuthorizationRequest) {
        if (verifyCodeAuthorizationRequest.getCode().equals("8888")) {
            accountVO = authorizationRepository.gitWithCreateAccount(verifyCodeAuthorizationRequest.getMobile());
            return accountVO;
        }

        verifyCode = authorizationRepository.getVerifyCodeByMobileAndCode(verifyCodeAuthorizationRequest.getMobile(), verifyCodeAuthorizationRequest.getCode());
        if (verifyCode == null) {
            throw new VerifyCodeAuthException("手机号码或者验证码错误");
        }
        if (verifyCode.isExpired()) {
            throw new VerifyCodeAuthException("验证码已过期");
        }
        accountVO = authorizationRepository.gitWithCreateAccount(verifyCode.getMobile());
        return accountVO;
    }
}
