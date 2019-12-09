package study.daydayup.wolf.business.account.biz.api;

import study.daydayup.wolf.business.account.api.service.auth.AuthService;
import study.daydayup.wolf.framework.rpc.RpcService;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/12/5 7:36 下午
 **/
@RpcService(protocol = "dubbo")
public class AuthServiceImpl implements AuthService {
    @Override
    public void logout(long accountId) {

    }
}
