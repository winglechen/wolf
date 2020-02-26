package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.Data;
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
public class PaymentLog implements Model {
    private String paymentNo;

    private Long payerId;
    private Long payeeId;

    private String tradeNo;
    private String outTradeNo;

    private Integer state;
    private Integer logType;
    private Integer paymentMethod;

    private String data;
    private String tags;

    private LocalDateTime createdAt;
}
