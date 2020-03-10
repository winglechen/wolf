package study.daydayup.wolf.business.uc.api.crm.customer.credit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditLog implements Model {

    private Long orgId;
    private Long accountId;
    private Integer operationType;

    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;

    private String tags;
    private String memo;

    private Integer sourceVersion;
    private Integer targetVersion;

    private Long editor;
    private Integer deleteFlag;
    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;


}