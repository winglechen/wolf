package study.daydayup.wolf.business.uc.crm.biz.customer.credit.repository;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter.CreditLineConverter;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.converter.CreditLogConverter;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditLineDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao.CreditLogDAO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLineDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dataobject.CreditLogDO;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity.CreditLineEntity;
import study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity.CreditLineFactory;
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

    public int save(@NonNull CreditLineEntity entity) {
        if (entity.isNew()) {
            return add(entity);
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

    public CreditLineEntity find(@NonNull Long accountId, @NonNull Long orgId) {
        CreditConfig config = configService.find(orgId);
        CreditLine line = findCreditLine(accountId, orgId);
        if (line == null) {
            return CreditLineFactory.create(accountId, orgId, config);
        }

        return new CreditLineEntity(line, config);
    }

    public int add(@NonNull CreditLineEntity entity) {
        if (!entity.isNew()) {
            return 0;
        }
        CreditLineDO changesDO = CreditLineConverter.toDO(entity.getModel());
        return dao.insertSelective(changesDO);
    }

    private CreditLine findCreditLine(@NonNull Long accountId, @NonNull Long orgId) {
        CreditLineDO lineDO = dao.selectByAccountIdAndOrgId(accountId, orgId);
        return CreditLineConverter.toModel(lineDO);
    }

    private void logChanges(CreditLineEntity entity) {
        CreditLog creditLog = entity.getChanges().getCreditLog();
        if (null == creditLog) {
            return;
        }

        CreditLogDO logDO = CreditLogConverter.toDO(creditLog);
        logDAO.insertSelective(logDO);
    }
}
