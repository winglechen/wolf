package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.entity.PaymentLog;
import study.daydayup.wolf.business.pay.api.enums.NotifyReturnEnum;
import study.daydayup.wolf.business.pay.api.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.api.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentRepository;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.razorpay
 *
 * @author Wingle
 * @since 2020/2/29 11:41 下午
 **/
@Component
@Slf4j
public class RazorSubscriber {
    private static final PaymentMethodEnum PAYMENT_METHOD = PaymentMethodEnum.RAZORPAY;
    private static final String SEPARATOR = ":";

    @Resource
    private PaymentRepository paymentRepository;
    @Resource
    private PaymentLogRepository logRepository;
    @Resource
    private RazorConfig config;

    private JSONObject notify;

    public int subscribe(@NonNull String eventId, @NonNull String signature, @NonNull String data) {
        logResponse(eventId, signature, data);
        if (isDuplicate(eventId)) {
            return NotifyReturnEnum.DUPLICATE.getCode();
        }

        if (!verifyResponse(signature, data)) {
            return NotifyReturnEnum.FAIL.getCode();
        }

        if (!validateResponse(data)) {
            return NotifyReturnEnum.PARSE_ERROR.getCode();
        }

        return updatePayment(data);
    }

    private void logResponse(String eventId, String signature,String data) {
        PaymentLog log = PaymentLog.builder()
                .logType(PaymentLogTypeEnum.PAY_RETURN.getCode())
                .paymentMethod(PAYMENT_METHOD.getCode())
                .data(StringUtil.join(SEPARATOR, eventId, signature, data))
                .build();
        logRepository.add(log);
    }

    private boolean isDuplicate(String eventId) {
        return false;
    }

    private boolean verifyResponse(String signature,String data) {
        if (StringUtil.isEmpty(signature, true) || StringUtil.isEmpty(data, true)) {
            return false;
        }

        String secret = config.getWebHookSecret();
        try {
            return Utils.verifyWebhookSignature(data, signature, secret);
        } catch (RazorpayException e) {
            return false;
        }
    }


    private boolean validateResponse(String data) {
        notify = JSON.parseObject(data);
        if (notify == null) {
            return false;
        }


        return true;
    }

    private int updatePayment(String data) {



        return NotifyReturnEnum.SUCCESS.getCode();
    }
}
