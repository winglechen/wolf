package study.daydayup.wolf.business.pay.api.dto.india;

import lombok.Data;
import study.daydayup.wolf.framework.layer.api.DTO;

/**
 * study.daydayup.wolf.business.pay.api.dto.china
 *
 * @author Wingle
 * @since 2020/2/26 11:12 下午
 **/
@Data
public class RazorPayDTO implements DTO {
    private String paymentNo;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
}
