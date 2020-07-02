package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.pay.api.domain.entity
 *
 * @author Wingle
 * @since 2020/2/26 4:07 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Model {
    private Long id;
    private String paymentNo;

    private Long payeeId;
    private String payeeName;
    private Long payerId;
    private String payerName;
    private String payerPhone;
    private String payerEmail;

    private String tradeNo;
    private String outTradeNo;
    private LocalDateTime paidAt;
    private LocalDateTime outPaidAt;

    private Long goodsId;
    private String goodsName;
    private String goodsDescription;

    private BigDecimal amount;
    private Integer currency;
    private Integer state;
    private Integer paymentType;
    private Integer paymentMethod;
    private String paymentMode;

    private String attachment;
    private String tags;

    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
