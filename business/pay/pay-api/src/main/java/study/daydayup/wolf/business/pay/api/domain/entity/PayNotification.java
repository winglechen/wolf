package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.pay.api.dto.china
 *
 * @author Wingle
 * @since 2020/2/26 11:12 下午
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayNotification implements Model {
    /**
     * order.paid or payout.processed or ...
     */
    private String event;

    private Integer paymentMethod;
    private String paymentNo;
    /**
     * payment.id or payout.id
     */
    private String outTradeNo;
    private LocalDateTime outPaidAt;
    /**
     * paid | processed
     */
    private String status;
    private BigDecimal amount;

    private String outOrderNo;
}
