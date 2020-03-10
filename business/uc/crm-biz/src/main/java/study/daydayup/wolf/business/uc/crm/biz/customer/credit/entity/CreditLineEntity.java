package study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.enums.CreditOperationEnum;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.framework.layer.domain.AbstractEntity;
import study.daydayup.wolf.framework.layer.domain.Entity;

import java.math.BigDecimal;
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
        if (!isPromotable(amount)) {
            return;
        }

        initAmount(baseAmount);

        BigDecimal changeAmount = calculatePromoteAmount(amount);
        changeAmount = checkMaxAmountLimit(changeAmount);

        setChangeAmount(changeAmount);
        addPromotionFreq();
        logChanges(CreditOperationEnum.PROMOTION);
    }

    public void demote(@NonNull BigDecimal amount) {
        if (!isDemotable(amount)) {
            return;
        }

        BigDecimal changeAmount = calculateDemoteAmount(amount);
        changeAmount = checkMinAmountLimit(changeAmount);

        setChangeAmount(changeAmount);
        logChanges(CreditOperationEnum.DEMOTION);
    }

    private void addPromotionFreq() {
        initChanges();
        changes.setTimesLatestDay(model.getTimesLatestDay() + 1);

        //set week's month's and year's freq
    }

    private boolean isPromotable(@NonNull BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (!config.getEnable()) {
            return true;
        }

        if (checkPromoteFrequencyLimit()) {
            return false;
        }

        BigDecimal maxAmount = config.getMaxAmount();
        if (null == maxAmount) {
            return true;
        }

        if (amount.compareTo(maxAmount) > 0) {
            return false;
        }

        BigDecimal modelAmount = model.getAmount();
        if (null == modelAmount) {
            return true;
        }

        return modelAmount.compareTo(maxAmount) >= 0;
    }

    private boolean isDemotable(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        return !hasInvalidAmount();
    }

    private void initChanges() {
        if (changes != null) {
            return;
        }

        changes = new CreditLine();
    }

    private void setChangeAmount(BigDecimal changeAmount) {
        initChanges();

        changeAmount = DecimalUtil.scale(changeAmount);
        model.setAmount(changeAmount);
        changes.setAmount(changeAmount);
    }

    private boolean checkPromoteFrequencyLimit() {
        if (isNew) {
            return false;
        }

        if (!config.getEnable()) {
            return false;
        }

        Integer maxDayFreq =config.getMaxTimesPerDay();
        if (null == maxDayFreq || maxDayFreq <= 0) {
            return false;
        }

        Integer timesLatestDay = model.getTimesLatestDay();
        if (timesLatestDay == null) {
            return false;
        }

        return timesLatestDay >= maxDayFreq;
    }

    private BigDecimal calculatePromoteAmount(BigDecimal amount) {
        if (hasInvalidAmount()) {
            return amount;
        }

        return model.getAmount().add(amount);
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
        BigDecimal maxAmount = config.getMaxAmount();
        if (null == maxAmount || maxAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return changeAmount;
        }

        if (changeAmount.compareTo(maxAmount) <= 0) {
            return changeAmount;
        }

        return maxAmount;
    }

    private BigDecimal checkMinAmountLimit(BigDecimal changeAmount) {

        BigDecimal minAmount = config.getMinAmount();
        if (null != minAmount && minAmount.compareTo(changeAmount) > 0) {
            changeAmount = minAmount;
        }

        return changeAmount;
    }

    private boolean hasInvalidAmount() {
        if (isNew) {
            return true;
        }

        BigDecimal modelAmount = model.getAmount();
        return modelAmount.compareTo(BigDecimal.ZERO) <= 0;
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
        if (!isLoggable()) {
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

    private boolean isLoggable() {
        if (isNew) {
            return false;
        }

        return null != changes;
    }

}
