package study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator;

import study.daydayup.wolf.demo.account.api.dto.request.authorization.AuthorizationRequest;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordAuthenticator implements Authenticator {


    @Override
    public AccountVO authenticate(AuthorizationRequest request) {
        return null;
    }
}
