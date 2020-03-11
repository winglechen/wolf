package study.daydayup.wolf.business.uc.api.crm.customer.credit.service;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.uc.api.crm.customer.credit.service
 *
 * @author Wingle
 * @since 2020/3/10 1:03 下午
 **/
public interface CreditConfigService extends Service {
    Result<CreditConfig> find(Long orgId);
    Result<Integer> save(CreditConfig config);
}
