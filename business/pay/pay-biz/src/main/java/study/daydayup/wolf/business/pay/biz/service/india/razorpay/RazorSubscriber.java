package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.domain.entity.PayNotification;
import study.daydayup.wolf.business.pay.api.domain.entity.PaymentLog;
import study.daydayup.wolf.business.pay.api.domain.enums.NotifyReturnEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.handler.PaymentHandler;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.handler.PayoutHandler;
import study.daydayup.wolf.common.model.type.string.Decimal;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.common.util.lang.JsonUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private PayNotification notification;

    @Resource
    private PaymentLogRepository logRepository;
    @Resource
    private RazorConfig config;
    @Resource
    private PayoutHandler payoutHandler;
    @Resource
    private PaymentHandler paymentHandler;


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

        return updatePayment();
    }

    private void logResponse(String eventId, String signature,String data) {
        PaymentLog log = PaymentLog.builder()
                .logType(PaymentLogTypeEnum.PAY_RETURN.getCode())
                .paymentMethod(PAYMENT_METHOD.getCode())
                .data(StringUtil.joinWith(SEPARATOR, eventId, signature, data))
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
        JSONObject result = JSON.parseObject(data);
        if (result == null) {
            return false;
        }

        notification = new PayNotification();

        parseEvent(result);
        parsePayment(result);
        parsePayout(result);
        parseOrder(result);

        return validateNotify();
    }

    private void parseEvent(JSONObject result) {
        String value = result.getString("event");
        if (StringUtil.isEmpty(value)) {
            return ;
        }
        notification.setEvent(value);
    }

    private void parsePayment(JSONObject result) {
        JSONObject payment = JsonUtil.getJSONObject(result, "payload.payment.entity");
        if (payment == null) {
            return;
        }

        notification.setOutTradeNo(payment.getString("id"));
        notification.setAmount(parseAmount(payment.getLongValue("amount")));
        notification.setOutOrderNo(payment.getString("order_id"));
    }

    private BigDecimal parseAmount(long amount) {
        if (amount < 0) {
            return null;
        }

        if (amount == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal result = BigDecimal.valueOf(amount);
        result = result.divide(Decimal.HUNDRED, RoundingMode.HALF_UP);

        return DecimalUtil.scale(result);
    }

    private void parsePayout(JSONObject result) {
        JSONObject payout = JsonUtil.getJSONObject(result, "payload.payout.entity");
        if (payout == null) {
            return;
        }

        notification.setPaymentNo(payout.getString("reference_id"));
        notification.setOutTradeNo(payout.getString("id"));
        notification.setAmount(parseAmount(payout.getLongValue("amount_paid")));
        notification.setStatus(payout.getString("status"));
    }

    private void parseOrder(JSONObject result) {
        JSONObject order = JsonUtil.getJSONObject(result, "payload.order.entity");
        if (order == null) {
            return;
        }

        notification.setPaymentNo(order.getString("receipt"));
        notification.setOutOrderNo(order.getString("id"));
        notification.setAmount(parseAmount(order.getLongValue("amount_paid")));
        notification.setStatus(order.getString("status"));
    }

    private boolean validateNotify() {
        if (StringUtil.isEmpty(notification.getEvent())) {
            return false;
        }

        if (StringUtil.isEmpty(notification.getPaymentNo())) {
            return false;
        }

        if (StringUtil.isEmpty(notification.getOutTradeNo())) {
            return false;
        }

        if (StringUtil.isEmpty(notification.getStatus())) {
            return false;
        }

        if (null == notification.getAmount()) {
            return false;
        }

        if (notification.getAmount().compareTo(BigDecimal.ZERO) < 1) {
            return false;
        }

        return true;
    }

    private int updatePayment() {

        switch (notification.getEvent()) {
            case "order.paid":
                return paymentHandler.handle(notification);
            case "payout.processed":
                return payoutHandler.handle(notification);
            default:
                return NotifyReturnEnum.SUCCESS.getCode();
        }
    }
}
