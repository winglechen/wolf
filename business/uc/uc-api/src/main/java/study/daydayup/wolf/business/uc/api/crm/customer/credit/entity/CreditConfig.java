package study.daydayup.wolf.business.uc.api.crm.customer.credit.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreditConfig implements Model {

    @NotNull @Min(1)
    private Long orgId;

    private Boolean enable;
    private BigDecimal promotePerOrder;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minPromotionPeriod;

    private Integer maxTimesPerDay;
    private Integer maxTimesPerWeek;
    private Integer maxTimesPerMonth;
    private Integer maxTimesPerYear;

    private Integer version;
    private Integer deleteFlag;
    private Long lastEditor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}