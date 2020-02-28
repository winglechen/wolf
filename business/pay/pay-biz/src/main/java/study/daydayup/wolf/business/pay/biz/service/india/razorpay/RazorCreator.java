package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.exception.InvalidPayConfigException;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/2/28 10:44 上午
 **/
@Component
public class RazorCreator {
    @Resource
    private RazorConfig config;

    public PaymentCreateResponse create(PaymentCreateRequest request) {
        JSONObject options = new JSONObject();
        options.put("amount", 1000);
        options.put("currency", "INR");
        options.put("receipt", "paymentNo");
        options.put("payment_capture", true);

        try {
            RazorpayClient client = new RazorpayClient(config.getKeyId(), config.getKeySecret());
            Order order = client.Orders.create(options);
        } catch (RazorpayException e) {
            throw new InvalidPayConfigException("Razorpay init fail");
        }




        return null;
    }
}
