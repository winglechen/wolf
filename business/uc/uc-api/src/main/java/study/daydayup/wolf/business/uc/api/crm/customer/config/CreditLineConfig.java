package study.daydayup.wolf.business.uc.api.crm.customer.config;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.uc.api.crm.customer.config
 *
 * @author Wingle
 * @since 2020/3/6 4:59 下午
 **/
@Data
@Builder
public class CreditLineConfig {
    private Boolean enable = false;
    private BigDecimal promotePerOrder;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Integer minPromotionPeriod = 0;

    private Integer maxTimesPerDay = 1;
    private Integer maxTimesPerWeek = 1;
    private Integer maxTimesPerMonth = 1;
    private Integer maxTimesPerYear = 1;

}
