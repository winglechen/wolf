package study.daydayup.wolf.business.uc.crm.biz.customer.credit.api;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.service.CreditLineService;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditLineDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLineDO;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.api
 *
 * @author Wingle
 * @since 2020/3/10 1:16 下午
 **/
@RpcService(protocol = "dubbo")
public class CreditLineServiceImpl implements CreditLineService {
    @Resource
    private CreditLineDAO dao;

    @Override
    public CreditLine find(@NonNull Long accountId, @NonNull Long orgId) {
        CreditLineDO lineDO;
        return null;
    }

    @Override
    public List<CreditLine> findByAccounts(Collection<Long> accountIds, Long orgId) {
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
