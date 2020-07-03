package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.DoublePayingException;
import study.daydayup.wolf.business.pay.api.domain.exception.epi.InvalidEpiResponseException;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayConfigException;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentCreator;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.util.collection.ArrayUtil;
import study.daydayup.wolf.common.util.lang.BeanUtil;
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
    private static final String CURRENCY = "INR";
    private static final String[] STATUS_ARRAY = new String[] {
            "created", "attempted", "paid"
    };

    //TODO use PayConfig
    @Resource
    private RazorConfig razorConfig;

    private Order order;
    private int amount;

    public void callPayEpi() {
        JSONObject options = new JSONObject();
        options.put("amount", getAmount());
        options.put("currency", CURRENCY);
        options.put("receipt", payment.getPaymentNo());
        options.put("payment_capture", true);

        try {
            RazorpayClient client = new RazorpayClient(razorConfig.getKeyId(), razorConfig.getKeySecret());
            order = client.Orders.create(options);
            apiResponse = order.toString();
        } catch (RazorpayException e) {
            throw new InvalidPayConfigException("Razorpay init fail");
        }

    }

    @Override
    public void parseCreateResponse() {
        parseOrder(order);
    }

    private void parseOrder(Order order) {
        if (!validOrder(order)) {
            throw new InvalidEpiResponseException("Razorpay fail");
        }

        setPaymentAttachment(order);
        setResponseAttachment(order);
    }

    private void setPaymentAttachment(Order order) {
        if (BeanUtil.equals("paid", (String)order.get("status"))) {
            throw new DoublePayingException(payment.getTradeNo());
        }

//        if (BeanUtil.equals("attempted", (String)order.get("status"))) {
//            payment.setState(PaymentStateEnum.PAYING.getCode());
//        }

        ObjectMap map = new ObjectMap();
        map.set("order_id", order.get("id"))
                .set("created_at", order.get("created_at"));

        payment.setOutTradeNo(order.get("id"));
        payment.setAttachment(map.toJson());
    }

    private void setResponseAttachment(Order order) {
        attachment.set("description", razorConfig.getCompanyDescription());
        attachment.set("image", razorConfig.getCompanyLogo());
        attachment.set("currency", CURRENCY);

        attachment.set("key", razorConfig.getKeyId());
        attachment.set("amount", String.valueOf(amount));
        attachment.set("name", razorConfig.getCompanyName());
        attachment.set("order_id", order.get("id"));

        attachment.set("prefill", razorConfig.getPrefill());
        attachment.set("theme", razorConfig.getTheme());
    }

    private boolean validOrder(Order order) {
        if (null == order) {
            return false;
        }

        if (!order.has("status") || !order.has("id")) {
            return false;
        }

        if (!BeanUtil.equals(order.get("amount"), amount)) {
            return false;
        }

        if (!payment.getPaymentNo().equals(order.get("receipt"))) {
            return false;
        }

        if (!ArrayUtil.inArray((String)order.get("status"), STATUS_ARRAY)) {
            return false;
        }

        return true;
    }

}
