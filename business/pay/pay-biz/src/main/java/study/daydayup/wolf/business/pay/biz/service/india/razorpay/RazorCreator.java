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
import study.daydayup.wolf.business.pay.biz.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.service.PaymentCreator;
import study.daydayup.wolf.common.util.lang.DecimalUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/2/28 10:44 上午
 **/
@Component
public class RazorCreator extends AbstractPaymentCreator implements PaymentCreator {
    @Resource
    private RazorConfig config;

    public void callPayApi() {
        JSONObject options = new JSONObject();
        options.put("amount", getAmount());
        options.put("currency", "INR");
        options.put("receipt", payment.getPaymentNo());
        options.put("payment_capture", true);

        try {
            RazorpayClient client = new RazorpayClient(config.getKeyId(), config.getKeySecret());
            Order order = client.Orders.create(options);
            formatOrder(order);
        } catch (RazorpayException e) {
            throw new InvalidPayConfigException("Razorpay init fail");
        }

    }

    private void formatOrder(Order order) {

    }

    private long getAmount() {
        BigDecimal amount = request.getAmount();
        amount = amount.multiply(BigDecimal.valueOf(100));

        return DecimalUtil.toLong(amount);
    }
}
