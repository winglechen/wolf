package study.daydayup.wolf.business.pay.api.dto.base.pay;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:53 下午
 **/
@Data
public class PaymentQuery implements Request {

    private Long payeeId;
    private Integer paymentChannel;

    private String paymentNo;
    private String tradeNo;
    private String outTradeNo;

    
}
