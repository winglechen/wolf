package study.daydayup.wolf.business.pay.api.entity;

import lombok.Builder;
import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.pay.api.entity
 *
 * @author Wingle
 * @since 2020/2/26 4:07 下午
 **/
@Data
@Builder
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
