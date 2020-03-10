package study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity;

import lombok.Getter;
import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity
 *
 * @author Wingle
 * @since 2020/3/10 4:59 下午
 **/
public class CreditLineEntity extends AbstractEntity<CreditLine> implements Entity {
    private CreditConfig config;


    public CreditLineEntity(@NonNull CreditLine line, @NonNull CreditConfig config) {
        this(line, config, false);
    }

    public CreditLineEntity(@NonNull CreditLine line, @NonNull CreditConfig config, boolean isNew) {
        this.config = config;

        model = line;
        key = CreditLine.builder()
                .accountId(line.getAccountId())
                .orgId(line.getOrgId())
                .version(line.getVersion())
                .build();
        this.isNew = isNew;
    }

    public void promote(@NonNull BigDecimal amount, BigDecimal baseAmount) {
        initAmount(baseAmount);

        logChanges();
    }

    public void initAmount(BigDecimal baseAmount) {
        if (baseAmount == null) {
            return;
        }

        BigDecimal amount = model.getAmount();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            model.setAmount(baseAmount);
        } else {
            amount = amount.add(baseAmount);
            amount = DecimalUtil.scale(amount);
            model.setAmount(amount);
        }
    }

    public void demote(BigDecimal amount) {
        if (isNew) {
            return;
        }

        logChanges();
    }


    private void logChanges() {
        if (isNew) {
            return;
        }
    }


}
