package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import lombok.NonNull;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.domain.exception.epi.InvalidEpiResponseException;
import study.daydayup.wolf.common.util.collection.MapUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/2/29 11:41 下午
 **/
@Component
public class RazorPayer {
    private static final String[] SIGN_KEYS = new String[]{"razorpay_signature", "razorpay_order_id", "razorpay_payment_id"};

    @Resource
    private RazorConfig config;

    private PayVerifyRequest request;

    public PayVerifyResponse pay(@NonNull PayVerifyRequest request) {
        this.request = request;
        validRequest();

        return null;
    }

    private void validRequest() {
        if (!MapUtil.contains(request.getAttachment(), SIGN_KEYS)) {
            throw new InvalidEpiResponseException("can't find the signatures");
        }
    }

    private void verifySignature() {
        JSONObject attributes = new JSONObject(request.getAttachment());
        String apiSecret = config.getKeySecret();

        try {
            if (!Utils.verifyPaymentSignature(attributes, apiSecret)) {
                throw new InvalidEpiResponseException("can't find the signatures");
            }
        } catch (RazorpayException e) {
            throw new InvalidEpiResponseException("can't find the signatures");
        }
    }
}
