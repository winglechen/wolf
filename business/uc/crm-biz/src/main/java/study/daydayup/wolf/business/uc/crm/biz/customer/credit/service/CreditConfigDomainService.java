package study.daydayup.wolf.business.uc.crm.biz.customer.credit.service;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.service
 *
 * @author Wingle
 * @since 2020/3/10 6:52 下午
 **/
public interface CreditConfigDomainService extends Service {
    CreditConfig find(Long orgId);
}
