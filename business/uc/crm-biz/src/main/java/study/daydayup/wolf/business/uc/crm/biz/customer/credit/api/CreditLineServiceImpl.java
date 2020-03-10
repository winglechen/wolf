package study.daydayup.wolf.business.uc.crm.biz.customer.credit.api;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.service.CreditLineService;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.api
 *
 * @author Wingle
 * @since 2020/3/10 1:16 下午
 **/
@RpcService(protocol = "dubbo")
public class CreditLineServiceImpl implements CreditLineService {
    @Override
    public CreditLine find(Long accountId, Long orgId) {
        return null;
    }

    @Override
    public Page<CreditLine> findByAccount(Long accountId, PageRequest request) {
        return null;
    }

    @Override
    public Page<CreditLine> findByOrg(Long orgId, PageRequest request) {
        return null;
    }

    @Override
    public int promote(Long accountId, Long orgId, BigDecimal amount) {
        return 0;
    }

    @Override
    public int demote(Long accountId, Long orgId, BigDecimal amount) {
        return 0;
    }
}
