package study.daydayup.wolf.business.uc.crm.biz.customer.credit.api;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.business.uc.api.crm.customer.service.CreditLogService;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.api
 *
 * @author Wingle
 * @since 2020/3/10 1:20 下午
 **/
@RpcService(protocol = "dubbo")
public class CreditLogServiceImpl implements CreditLogService {
    @Override
    public Page<CreditLog> find(Long accountId, Long orgId, PageRequest request) {
        return null;
    }

    @Override
    public Page<CreditLog> findByAccount(Long accountId, PageRequest request) {
        return null;
    }

    @Override
    public Page<CreditLog> findByOrg(Long orgId, PageRequest request) {
        return null;
    }
}
