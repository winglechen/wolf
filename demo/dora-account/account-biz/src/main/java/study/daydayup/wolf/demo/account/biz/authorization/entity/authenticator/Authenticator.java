package study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator;

import study.daydayup.wolf.demo.account.api.dto.request.authorization.AuthorizationRequest;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;

public interface Authenticator<T extends AuthorizationRequest> {

    AccountVO authenticate(T request);

}
