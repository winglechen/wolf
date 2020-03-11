package study.daydayup.wolf.business.uc.crm.biz.customer.credit.entity;

import lombok.NonNull;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLog;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.enums.CreditOperationEnum;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.common.util.time.DateUtil;
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
    private LocalDateTime now;

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
        initNow();
    }

    /**
     * promote
     * @param amount NotNull
     * @param baseAmount Nullable
     */
    public void promote(@NonNull BigDecimal amount, BigDecimal baseAmount) {
        if (!isPromotable(amount)) {
            return;
        }

        initModelAmount(baseAmount);

        BigDecimal changeAmount = calculatePromoteAmount(amount);
        changeAmount = checkMaxAmountLimit(changeAmount);

        setAmount(changeAmount);
        setPromotionFreq();
        logChanges(CreditOperationEnum.PROMOTION);
    }

    /**
     * demote
     * @param amount NotNull
     */
    public void demote(@NonNull BigDecimal amount) {
        if (!isDemotable(amount)) {
            return;
        }

        BigDecimal changeAmount = calculateDemoteAmount(amount);
        changeAmount = checkMinAmountLimit(changeAmount);

        setAmount(changeAmount);
        logChanges(CreditOperationEnum.DEMOTION);
    }

    /**
     * leave the changes to null if no changes happen
     */
    private void initChanges() {
        if (changes != null) {
            return;
        }

        changes = new CreditLine();
    }

    private void initNow() {
        if (now != null) {
            return;
        }

        now = LocalDateTime.now();
    }

    /**
     * isPromotable =>
     *      1, amount is valid
     *      2, modelAmount < maxAmount
     *      3, not over freq limit
     * @param amount NotNull
     * @return boolean
     */
    private boolean isPromotable(@NonNull BigDecimal amount) {
        if (!isValidPromoteAmount(amount)) {
            return false;
        }

        if (isAlreadyOverMaxAmount()) {
            return false;
        }

        return !isOverPromoteFrequencyLimit();
    }

    /**
     * amount > 0 && modelAmount > 0
     * @param amount NotNull
     * @return boolean
     */
    private boolean isDemotable(@NonNull BigDecimal amount) {
        // amount > 0
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (isNew) {
            return false;
        }

        // modelAmount > 0
        BigDecimal modelAmount = model.getAmount();
        return modelAmount.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * modelAmount >= config.maxAmount
     * @return boolean
     */
    private boolean isAlreadyOverMaxAmount() {
        if (!config.getEnable()) {
            return false;
        }

        BigDecimal maxAmount = config.getMaxAmount();
        if (null == maxAmount || maxAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        BigDecimal modelAmount = model.getAmount();
        if (null == modelAmount || modelAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }


        return modelAmount.compareTo(maxAmount) >= 0;
    }

    /**
     * amount > 0 && amount < config.maxAmount
     * @param amount promoteAmount
     * @return boolean
     */
    private boolean isValidPromoteAmount(@NonNull BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (!config.getEnable()) {
            return true;
        }

        BigDecimal maxAmount = config.getMaxAmount();
        if (null == maxAmount || maxAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return true;
        }

        return amount.compareTo(maxAmount) <= 0;
    }

    /**
     * Just ModelAmount + amount (will validate later)
     * @param amount NotNull
     * @return newAmount
     */
    private BigDecimal calculatePromoteAmount(@NonNull BigDecimal amount) {
        if (isNew) {
            return amount;
        }

        BigDecimal modelAmount = model.getAmount();
        if (null == modelAmount || modelAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return amount;
        }

        return model.getAmount().add(amount);
    }

    /**
     * Min( (modelAmount - amount) , 0 )
     * @param amount NotNull
     * @return Min( (modelAmount - amount) , 0 )
     */
    private BigDecimal calculateDemoteAmount(@NonNull BigDecimal amount) {
        BigDecimal modelAmount = model.getAmount();
        BigDecimal changeAmount = BigDecimal.ZERO;

        if (modelAmount.compareTo(amount) > 0) {
            changeAmount = modelAmount.subtract(amount);
        }

        return changeAmount;
    }

    /**
     * Min(changeAmount, maxAmount)
     * @param changeAmount NotNull
     * @return Min(changeAmount, maxAmount)
     */
    private BigDecimal checkMaxAmountLimit(@NonNull BigDecimal changeAmount) {
        if (!config.getEnable()) {
            return changeAmount;
        }

        BigDecimal maxAmount = config.getMaxAmount();
        if (null == maxAmount || maxAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return changeAmount;
        }

        if (changeAmount.compareTo(maxAmount) <= 0) {
            return changeAmount;
        }

        return maxAmount;
    }

    /**
     * Max(changeAmount, minAmount)
     * @param changeAmount NotNull
     * @return Max(changeAmount, minAmount)
     */
    private BigDecimal checkMinAmountLimit(@NonNull BigDecimal changeAmount) {
        if (!config.getEnable()) {
            return changeAmount;
        }

        BigDecimal minAmount = config.getMinAmount();
        if (null == minAmount || minAmount.compareTo(BigDecimal.ZERO) > 0) {
            return changeAmount;
        }

        if (changeAmount.compareTo(minAmount) >= 0) {
            return changeAmount;
        }

        return minAmount;
    }

    /**
     * Just verify and Can not change the model
     * @return boolean
     */
    private boolean isOverPromoteFrequencyLimit() {
        if (!config.getEnable()) {
            return false;
        }

        if (isNew) {
            return false;
        }


        LocalDateTime promoteAt = model.getPromotedAt();
        if (promoteAt == null) {
            return false;
        }

        if (isOverMinPromotionPeriod()) {
            return true;
        }

        if (isOverDayFreq()) {
            return true;
        }

        if (isOverWeekFreq()) {
            return true;
        }

        if (isOverMonthFreq()) {
            return true;
        }

        return isOverYearFreq();
    }

    /**
     * Will not check config.enable && entity.isNew
     * @return boolean
     */
    private boolean isOverMinPromotionPeriod() {
        Integer minPromotionPeriod = config.getMinPromotionPeriod();
        if (minPromotionPeriod == null || minPromotionPeriod <= 0) {
            return false;
        }

        LocalDateTime latestPromoteTime = model.getPromotedAt().plusSeconds(minPromotionPeriod);
        return now.compareTo(latestPromoteTime) < 0;
    }

    private boolean isOverDayFreq() {
        Integer maxTimesPerDay = config.getMaxTimesPerDay();
        if (maxTimesPerDay == null || maxTimesPerDay <= 0) {
            return false;
        }

        if (!DateUtil.isSameDay(model.getPromotedAt(), now)) {
            return false;
        }

        return model.getTimesLatestDay() + 1 > maxTimesPerDay;
    }

    private boolean isOverWeekFreq() {
        Integer maxTimesPerWeek = config.getMaxTimesPerWeek();
        if (maxTimesPerWeek == null || maxTimesPerWeek <= 0) {
            return false;
        }

        if (!DateUtil.isSameWeek(model.getPromotedAt(), now)) {
            return false;
        }

        return model.getTimesLatestWeek() + 1 > maxTimesPerWeek;
    }

    private boolean isOverMonthFreq() {
        Integer maxTimesPerMonth = config.getMaxTimesPerMonth();
        if (maxTimesPerMonth == null || maxTimesPerMonth <= 0) {
            return false;
        }

        if (!DateUtil.isSameMonth(model.getPromotedAt(), now)) {
            return false;
        }

        return model.getTimesLatestMonth() + 1 > maxTimesPerMonth;
    }

    private boolean isOverYearFreq() {
        Integer maxTimesPerYear = config.getMaxTimesPerYear();
        if (maxTimesPerYear == null || maxTimesPerYear <= 0) {
            return false;
        }

        if (!DateUtil.isSameYear(model.getPromotedAt(), now)) {
            return false;
        }

        return model.getTimesLatestYear() + 1 > maxTimesPerYear;
    }

    /**
     * initModelAmount if baseAmount != null && entity.isNew
     * @param baseAmount Nullable
     */
    private void initModelAmount(BigDecimal baseAmount) {
        if (baseAmount == null) {
            return;
        }

        BigDecimal modelAmount = model.getAmount();
        if (null != modelAmount && modelAmount.compareTo(BigDecimal.ZERO) > 0) {
            return;
        }

        model.setAmount(DecimalUtil.scale(baseAmount));
    }

    /**
     * Just Change amount of Model and changes
     * @param changeAmount NotNull
     */
    private void setAmount(BigDecimal changeAmount) {
        initChanges();

        changeAmount = DecimalUtil.scale(changeAmount);
        model.setAmount(changeAmount);
        changes.setAmount(changeAmount);
    }

    private void setPromotionFreq() {
        initChanges();

        setMaxTimesPerDay();
        setMaxTimesPerWeek();
        setMaxTimesPerMonth();
        setMaxTimesPerYear();

        setPromoteAt();
    }

    private void setMaxTimesPerDay() {
        int times = model.getTimesLatestDay();
        if (DateUtil.isSameDay(model.getPromotedAt(), now)) {
            times = times + 1;
        } else {
            times = 1;
        }

        model.setTimesLatestDay(times);
        changes.setTimesLatestDay(times);
    }

    private void setMaxTimesPerWeek() {
        int times = model.getTimesLatestWeek();
        if (DateUtil.isSameWeek(model.getPromotedAt(), now)) {
            times = times + 1;
        } else {
            times = 1;
        }

        model.setTimesLatestWeek(times);
        changes.setTimesLatestWeek(times);
    }

    private void setMaxTimesPerMonth() {
        int times = model.getTimesLatestMonth();
        if (DateUtil.isSameMonth(model.getPromotedAt(), now)) {
            times = times + 1;
        } else {
            times = 1;
        }

        model.setTimesLatestMonth(times);
        changes.setTimesLatestMonth(times);
    }

    private void setMaxTimesPerYear() {
        int times = model.getTimesLatestYear();
        if (DateUtil.isSameYear(model.getPromotedAt(), now)) {
            times = times + 1;
        } else {
            times = 1;
        }

        model.setTimesLatestYear(times);
        changes.setTimesLatestYear(times);
    }

    private void setPromoteAt() {
        changes.setPromotedAt(now);
        changes.setUpdatedAt(now);

        model.setPromotedAt(now);
        model.setUpdatedAt(now);
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
