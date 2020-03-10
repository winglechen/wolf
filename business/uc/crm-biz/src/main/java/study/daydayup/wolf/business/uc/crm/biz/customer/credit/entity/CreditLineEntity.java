package study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity;

import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity
 *
 * @author Wingle
 * @since 2020/3/10 4:59 下午
 **/
public class CreditLineEntity extends AbstractEntity<CreditLine> implements Entity {
    private CreditConfig config;
    private CreditLog creditLog;

    public CreditLineEntity(CreditLine line) {
        model = line;
        key = CreditLine.builder()
                .accountId(line.getAccountId())
                .orgId(line.getOrgId())
                .build();
        isNew = false;
    }

    public boolean promote(BigDecimal amount) {
        return true;
    }

    public boolean demote(BigDecimal amount) {
        return true;
    }


}
