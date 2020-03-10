package study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity;

import lombok.Getter;
import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.enums.CreditOperationEnum;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;

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
        if (checkPromoteFrequencyLimit()) {
            return;
        }

        initAmount(baseAmount);

        logChanges(CreditOperationEnum.PROMOTION);
    }

    public void demote(@NonNull BigDecimal amount) {
        if (!isDemotable()) {
            return;
        }

        BigDecimal changeAmount = calculateDemoteAmount(amount);
        changeAmount = checkMinAmountLimit(changeAmount);

        changeAmount = DecimalUtil.scale(changeAmount);
        changes.setAmount(changeAmount);

        logChanges(CreditOperationEnum.DEMOTION);
    }


    private boolean checkPromoteFrequencyLimit() {
        return false;
    }

    private BigDecimal calculatePromoteAmount(BigDecimal amount) {
        return null;
    }

    private BigDecimal calculateDemoteAmount(BigDecimal amount) {
        BigDecimal modelAmount = model.getAmount();
        BigDecimal changeAmount = BigDecimal.ZERO;

        if (modelAmount.compareTo(amount) > 0) {
            changeAmount = modelAmount.subtract(amount);
        }

        return changeAmount;
    }

    private BigDecimal checkMaxAmountLimit(BigDecimal changeAmount) {
        return null;
    }

    private BigDecimal checkMinAmountLimit(BigDecimal changeAmount) {
        BigDecimal minAmount = config.getMinAmount();
        if (null != minAmount && minAmount.compareTo(changeAmount) > 0) {
            changeAmount = minAmount;
        }

        return changeAmount;
    }

    private boolean isDemotable() {
        if (isNew) {
            return false;
        }

        BigDecimal modelAmount = model.getAmount();
        if (modelAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        return true;
    }

    private void initAmount(BigDecimal baseAmount) {
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

    private void logChanges(@NonNull CreditOperationEnum operation) {
        if (isNew) {
            return;
        }

        if (null == changes) {
            return;
        }

        CreditLog creditLog = CreditLog.builder()
                .orgId(model.getOrgId())
                .accountId(model.getAccountId())
                .operationType(operation.getCode())

                .sourceAmount(model.getAmount())
                .targetAmount(changes.getAmount())

                .sourceVersion(model.getVersion())
                .targetVersion(model.getVersion() + 1)
                .createdAt(LocalDateTime.now())

                .build();

        changes.setCreditLog(creditLog);
    }


}
