package study.daydayup.wolf.business.uc.api.crm.customer.credit.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreditLine implements Model {

    private Long orgId;
    private Long accountId;

    private BigDecimal amount;
    private Integer currency;

    private Integer timesLatestDay;
    private Integer timesLatestWeek;
    private Integer timesLatestMonth;
    private Integer timesLatestYear;
    private LocalDateTime promotedAt;

    private Integer version;
    private Integer deleteFlag;
    private Long lastEditor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private CreditLog creditLog;

    private static final long serialVersionUID = 1L;
}