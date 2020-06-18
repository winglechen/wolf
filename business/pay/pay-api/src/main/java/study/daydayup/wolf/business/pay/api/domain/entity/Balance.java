package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Balance implements Serializable {
    private Long id;

    private Long accountId;
    private String accountName;

    private Integer currency;
    private BigDecimal availableAmount;
    private BigDecimal blockedAmount;
    private BigDecimal unavailableAmount;

    private Integer lockerFlag;
    private String locker;
    private LocalDateTime lockedAt;

    private Integer version;
    private Boolean deleteFlag;
    private Long lastEditor;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final long serialVersionUID = 1L;

}