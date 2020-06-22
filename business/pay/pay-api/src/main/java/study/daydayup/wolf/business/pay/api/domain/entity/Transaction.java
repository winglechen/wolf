package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction implements Serializable {
    private Long id;

    private Long payeeId;
    private Long payerId;
    private String payerName;
    private String payerPhone;
    private String payerEmail;

    private String paymentNo;
    private Integer transactionType;
    private Integer paymentChannel;

    private Integer currency;
    private BigDecimal amount;

    private String settlementNo;
    private Integer settlementState;

    private Integer notifyState;
    private String attachment;

    private LocalDateTime settledAt;
    private LocalDateTime paidAt;
    private LocalDateTime outPaidAt;

    private Boolean deleteFlag;
    private LocalDateTime createdAt;

    private static final long serialVersionUID = 1L;

}