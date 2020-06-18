package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BalanceLog implements Serializable {
    private Long id;

    private Long accountId;
    private Integer currency;

    private BigDecimal sourceAmount;
    private BigDecimal targetAmount;

    private String memo;
    private Boolean deleteFlag;

    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;
}