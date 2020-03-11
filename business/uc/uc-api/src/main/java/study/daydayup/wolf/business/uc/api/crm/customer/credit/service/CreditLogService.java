package study.daydayup.wolf.business.uc.api.crm.customer.credit.service;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;


/**
 * study.daydayup.wolf.business.uc.api.crm.customer.credit.service
 *
 * @author Wingle
 * @since 2020/3/10 1:03 下午
 **/
public interface CreditLogService extends Service {
    Result<Page<CreditLog>> find(Long accountId, Long orgId, PageRequest request);
    Result<Page<CreditLog>> findByAccount(Long accountId, PageRequest request);
    Result<Page<CreditLog>> findByOrg(Long orgId, PageRequest request);
}
