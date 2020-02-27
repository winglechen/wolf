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
public class RazorOrderDTO implements DTO {
    private String razorpayKeyId;

    private String companyName;
    private String companyDescription;
    private String companyLogo;

    private String prefillName;
    private String prefillEmail;
    private String prefillContact;

    //card | netbanking | wallet | emi | upi
    private String prefillMethod;

    private String tradeNo;
    private String paymentNo;
    private String razorpayOrderId;

    private Long amount;
    private String currency = "INR";
}
