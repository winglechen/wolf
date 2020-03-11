package study.daydayup.wolf.business.uc.api.crm.customer.credit.service;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * study.daydayup.wolf.business.uc.api.crm.customer.credit.service
 *
 * @author Wingle
 * @since 2020/3/10 1:03 下午
 **/
public interface CreditLineService extends Service {
    Result<CreditLine> find(Long accountId, Long orgId);

    Result<List<CreditLine>> findByAccounts(Collection<Long> accountIds, Long orgId);
    Result<Page<CreditLine>> findByAccount(Long accountId, PageRequest request);
    Result<Page<CreditLine>> findByOrg(Long orgId, PageRequest request);

    Result<Integer> promote(Long accountId, Long orgId, BigDecimal amount);
    Result<Integer> promote(Long accountId, Long orgId, BigDecimal amount, BigDecimal baseAmount);
    Result<Integer> demote(Long accountId, Long orgId, BigDecimal amount);
}
