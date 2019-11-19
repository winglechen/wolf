package study.daydayup.wolf.business.account.biz.api;

import study.daydayup.wolf.business.account.api.entity.Account;
import study.daydayup.wolf.business.account.api.service.AccountService;
import study.daydayup.wolf.framework.rpc.RpcService;

/**
 * study.daydayup.wolf.business.account.biz.api
 *
 * @author Wingle
 * @since 2019/11/19 3:44 下午
 **/
@RpcService(protocol = "dubbo")
public class AccountServiceImpl implements AccountService {
    @Override
    public long create(Account account) {
        return 123;
    }
}
