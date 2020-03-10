package study.daydayup.wolf.business.uc.crm.biz.customer.credit.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter.CreditLineConverter;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter.CreditLogConverter;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditLineDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditLogDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLineDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLogDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity.CreditLineEntity;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.service.CreditConfigDomainService;
import study.daydayup.wolf.framework.layer.domain.AbstractRepository;
import study.daydayup.wolf.framework.layer.domain.Repository;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.repository
 *
 * @author Wingle
 * @since 2020/3/10 4:59 下午
 **/
@Component
public class CreditLineRepository extends AbstractRepository implements Repository {
    @Resource
    private CreditLineDAO dao;
    @Resource
    private CreditLogDAO logDAO;
    @Resource
    private CreditConfigDomainService configService;

    public int save(CreditLineEntity entity) {
        if (entity == null) {
            return 0;
        }

        CreditLine key = entity.getKey();
        CreditLine changes = entity.getChanges();
        if (null == key || null == changes) {
            return 0;
        }

        CreditLineDO changesDO = CreditLineConverter.toDO(changes);
        int status = dao.updateByAccountIdAndOrgId(changesDO, key.getAccountId(), key.getOrgId(), key.getVersion());
        logChanges(entity);

        return status;
    }

    private void logChanges(CreditLineEntity entity) {
        if (null == entity.getCreditLog()) {
            return;
        }

        CreditLogDO logDO = CreditLogConverter.toDO(entity.getCreditLog());
        logDAO.insertSelective(logDO);
    }

    public CreditLineEntity find(@NonNull Long accountId, @NonNull Long orgId) {
        CreditLine line = findCreditLine(accountId, orgId);
        if (line == null) {
            return null;
        }

        CreditConfig config = configService.find(orgId);
        return new CreditLineEntity(line, config);
    }

    private CreditLine findCreditLine(@NonNull Long accountId, @NonNull Long orgId) {
        CreditLineDO lineDO = dao.selectByAccountIdAndOrgId(accountId, orgId);
        return CreditLineConverter.toModel(lineDO);
    }
}
