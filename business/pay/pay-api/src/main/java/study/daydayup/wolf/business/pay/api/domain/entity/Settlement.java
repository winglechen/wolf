package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Settlement implements Serializable {
    private Long id;

    private Long accountId;

    private String settlementNo;
    private Integer settlementType;
    private Integer state;

    private Integer currency;

    private Integer transactionCount;
    private BigDecimal transactionAmount;

    private Integer deductCount;
    private BigDecimal deductAmount;

    private Integer feeCount;
    private BigDecimal feeAmount;

    private Integer taxCount;
    private BigDecimal taxAmount;

    private LocalDateTime settledAt;
    private Boolean deleteFlag;
    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;


}