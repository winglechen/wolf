package study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator;

import study.daydayup.wolf.demo.account.api.dto.request.auth.AuthRequest;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;

public interface Authenticator<T extends AuthRequest> {

    AccountVO authenticate(T request);

}
