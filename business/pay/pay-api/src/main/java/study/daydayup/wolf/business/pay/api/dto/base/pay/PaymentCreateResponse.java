package study.daydayup.wolf.business.pay.api.dto.base.pay;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.Response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.api.dto
 *
 * @author Wingle
 * @since 2020/2/27 3:54 下午
 **/
@Data
public class PaymentCreateResponse implements Response {
    private Integer paymentChannel;

    private String paymentNo;
    private BigDecimal amount;
    private Map<String, Object> payArgs;

    private String returnUrl;

    public PaymentCreateResponse() {
        payArgs = new HashMap<>();
    }


}
