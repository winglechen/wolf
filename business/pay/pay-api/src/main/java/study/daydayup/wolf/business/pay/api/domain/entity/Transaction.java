package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction implements Serializable {
    private Long id;

    private Long accountId;
    private Long payeeId;

    private String paymentNo;
    private Integer transactionType;
    private Integer paymentChannel;

    private Integer currency;
    private BigDecimal amount;

    private String settlementNo;
    private Integer settlementState;
    private LocalDateTime settledAt;

    private Boolean deleteFlag;
    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

}