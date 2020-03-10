package study.daydayup.wolf.business.uc.api.crm.customer.credit.service;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.uc.api.crm.customer.credit.service
 *
 * @author Wingle
 * @since 2020/3/10 1:03 下午
 **/
public interface CreditConfigService extends Service {
    CreditConfig find(Long orgId);
    int save(CreditConfig config);
}
