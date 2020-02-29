package study.daydayup.wolf.business.pay.api.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.pay.api.entity
 *
 * @author Wingle
 * @since 2020/2/26 4:07 下午
 **/
@Data
public class Payment implements Model {
    private String paymentNo;

    private Long payerId;
    private String payerName;
    private Long payeeId;
    private String payeeName;

    private String tradeNo;
    private String outTradeNo;

    private BigDecimal amount;
    private Integer currency;
    private Integer state;
    private Integer paymentType;
    private Integer paymentMethod;

    private String attachment;
    private String tags;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
