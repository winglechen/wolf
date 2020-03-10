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

    @NotNull
    private Boolean enable              = false;
    private Integer currency;

    private BigDecimal promotePerOrder  = BigDecimal.ZERO;
    private BigDecimal minAmount        = BigDecimal.ZERO;
    private BigDecimal maxAmount        = BigDecimal.ZERO;

    private Integer minPromotionPeriod  = 0;
    private Integer maxTimesPerDay      = 0;
    private Integer maxTimesPerWeek     = 0;
    private Integer maxTimesPerMonth    = 0;
    private Integer maxTimesPerYear     = 0;

    private Integer version;
    private Integer deleteFlag;
    private Long lastEditor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}