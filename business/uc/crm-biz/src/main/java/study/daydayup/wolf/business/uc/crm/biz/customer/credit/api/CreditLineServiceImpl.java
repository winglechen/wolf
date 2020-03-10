package study.daydayup.wolf.business.uc.crm.biz.customer.credit.api;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.service.CreditLineService;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter.CreditLineConverter;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditLineDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLineDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;
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
        CreditLineDO lineDO = dao.selectByAccountIdAndOrgId(accountId, orgId);
        return CreditLineConverter.toModel(lineDO);
    }

    @Override
    public List<CreditLine> findByAccounts(@NonNull Collection<Long> accountIds, @NonNull Long orgId) {
        if (CollectionUtil.isEmpty(accountIds)) {
            return ListUtil.empty();
        }

        List<CreditLineDO> lineDOList = dao.selectByAccountIdIn(accountIds, orgId);
        return CreditLineConverter.toModel(lineDOList);
    }

    @Override
    public Page<CreditLine> findByAccount(@NonNull Long accountId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());

        List<CreditLineDO> lineDOList = dao.selectByAccountId(accountId);
        if (CollectionUtil.isEmpty(lineDOList)) {
            return Page.empty(pageReq.getPageNum(), pageReq.getPageSize());
        }

        List<CreditLine> lineList = CreditLineConverter.toModel(lineDOList);
        return Page.of(lineDOList).to(lineList);
    }

    @Override
    public Page<CreditLine> findByOrg(Long orgId, PageRequest request) {
        //暂不提供
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
