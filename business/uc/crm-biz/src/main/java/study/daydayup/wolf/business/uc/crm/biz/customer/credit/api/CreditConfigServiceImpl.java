package study.daydayup.wolf.business.uc.crm.biz.customer.credit.api;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.service.CreditConfigService;
import study.daydayup.wolf.framework.rpc.RpcService;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.api
 *
 * @author Wingle
 * @since 2020/3/10 1:19 下午
 **/
@RpcService(protocol = "dubbo")
public class CreditConfigServiceImpl implements CreditConfigService {
    @Override
    public CreditConfig find(Long orgId) {
        return null;
    }

    @Override
    public int save(CreditConfig config) {
        return 0;
    }
}
