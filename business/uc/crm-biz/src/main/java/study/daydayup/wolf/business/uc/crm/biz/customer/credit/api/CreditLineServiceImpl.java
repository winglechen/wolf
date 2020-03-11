package study.daydayup.wolf.business.uc.crm.biz.customer.credit.api;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.service.CreditLineService;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter.CreditLineConverter;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditLineDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLineDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity.CreditLineEntity;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.repository.CreditLineRepository;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;
import study.daydayup.wolf.framework.rpc.Result;
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
    @Resource
    private CreditLineRepository repository;

    @Override
    public Result<CreditLine> find(@NonNull Long accountId, @NonNull Long orgId) {
        CreditLineDO lineDO = dao.selectByAccountIdAndOrgId(accountId, orgId);
        CreditLine line = CreditLineConverter.toModel(lineDO);
        return Result.ok(line);
    }

    @Override
    public Result<List<CreditLine>> findByAccounts(@NonNull Collection<Long> accountIds, @NonNull Long orgId) {
        if (CollectionUtil.isEmpty(accountIds)) {
            return Result.ok(ListUtil.empty());
        }

        List<CreditLineDO> lineDOList = dao.selectByAccountIdIn(accountIds, orgId);
        return Result.ok(
                CreditLineConverter.toModel(lineDOList)
        );
    }

    @Override
    public Result<Page<CreditLine>> findByAccount(@NonNull Long accountId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());

        List<CreditLineDO> lineDOList = dao.selectByAccountId(accountId);
        if (CollectionUtil.isEmpty(lineDOList)) {
            return Result.ok(
                    Page.empty(pageReq.getPageNum(), pageReq.getPageSize())
            );
        }

        List<CreditLine> lineList = CreditLineConverter.toModel(lineDOList);
        return Result.ok(
                Page.of(lineDOList).to(lineList)
        );
    }

    @Override
    public Result<Page<CreditLine>> findByOrg(Long orgId, PageRequest request) {
        //暂不提供
        return null;
    }

    @Override
    public Result<Integer> promote(@NonNull Long accountId, @NonNull Long orgId, @NonNull BigDecimal amount) {
        return promote(accountId, orgId, amount, null);
    }

    @Override
    public Result<Integer> promote(@NonNull Long accountId, @NonNull Long orgId, @NonNull BigDecimal amount, BigDecimal baseAmount) {
        CreditLineEntity entity = repository.find(accountId, orgId);
        if (entity == null) {
            return Result.ok(0);
        }

        entity.promote(amount, baseAmount);
        int status = repository.save(entity);
        return Result.ok(status);
    }

    @Override
    public Result<Integer> demote(@NonNull Long accountId, @NonNull Long orgId, @NonNull BigDecimal amount) {
        CreditLineEntity entity = repository.find(accountId, orgId);
        if (entity == null) {
            return Result.ok(0);
        }

        entity.demote(amount);
        int status = repository.save(entity);
        return Result.ok(status);
    }
}
