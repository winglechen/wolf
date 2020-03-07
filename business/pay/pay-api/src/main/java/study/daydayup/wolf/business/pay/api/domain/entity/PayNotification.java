package study.daydayup.wolf.business.pay.api.domain.entity;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Model;

import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.pay.api.dto.china
 *
 * @author Wingle
 * @since 2020/2/26 11:12 下午
 **/
@Data
public class PayNotification implements Model {
    /**
     * order.paid or payout.processed or ...
     */
    private String event;

    private String paymentNo;
    /**
     * payment.id or payout.id
     */
    private String outTradeNo;
    /**
     * paid | processed
     */
    private String status;
    private BigDecimal amount;

    private String outOrderNo;
}
