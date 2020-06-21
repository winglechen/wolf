package study.daydayup.wolf.business.pay.api.dto.base.pay;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:53 下午
 **/
@Data
public class PaymentQuery implements Request {

    protected Long payeeId;
    protected Integer paymentChannel;

    protected String payerId;
    protected String payerName;
    protected String payerPhone;
    protected String payerEmail;

    protected String paymentNo;
    protected String tradeNo;
    protected String outTradeNo;

    protected String createdBefore;
    protected String createdAfter;
    protected LocalDateTime createdStart;
    protected LocalDateTime createdEnd;

}
