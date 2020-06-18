package study.daydayup.wolf.business.pay.biz.service.india.cashfree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.exception.epi.InvalidEpiResponseException;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayConfigException;
import study.daydayup.wolf.business.pay.api.dto.india.IndianBankCard;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentCreator;
import study.daydayup.wolf.business.pay.biz.epi.india.IndianCustomerEpi;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.cashfree
 *
 * @author Wingle
 * @since 2020/6/7 10:01 下午
 **/
@Slf4j
@Component
public class CashfreeCreator extends AbstractPaymentCreator implements PaymentCreator {
    private static final String CONFIG_KEY = "cashfree";
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

    @Resource
    private IndianCustomerEpi indianCustomerEpi;

    @Override
    public void callPayEpi() {
        initConfig(CONFIG_KEY);
        Request payRequest = createRequest();

        try {
            Response response = CLIENT.newCall(payRequest).execute();
            parseResponse(response);
        } catch (Exception e) {
            log.error("Cashfree create exception ", e);
            throw new InvalidEpiResponseException("Cashfree creator call epi failed");
        }
    }

    @Override
    public void parseCreateResponse() {
        JSONObject json = JSON.parseObject(apiResponse);
        if (!isResponseSuccess(json)) {
            throw new InvalidEpiResponseException("Dokypay create response parse error");
        }

        setResponseAttachment(json);
    }

    private void setResponseAttachment(@NonNull JSONObject data) {
        attachment.put("payUrl", data.getString("paymentLink"));
        attachment.put("orderId", payment.getPaymentNo());
        attachment.put("orderAmount", getAmount());
        attachment.put("orderCurrency", "INR");
    }

    private boolean isResponseSuccess(JSONObject json) {
        if (null == json) {
            return false;
        }

        if (StringUtil.isBlank(json.getString("paymentLink"))) {
            return false;
        }

        return "OK".equalsIgnoreCase(json.getString("status"));
    }

    private void parseResponse(Response response) {
        if (null == response || !response.isSuccessful()) {
            throw new InvalidEpiResponseException("Cashfree create response is invalid");
        }

        try {
            apiResponse = Objects.requireNonNull(response.body()).string();
            log.info("Cashfree create response: {}", apiResponse);
        } catch (Exception e) {
            throw new InvalidEpiResponseException("Cashfree create responseBody is invalid");
        }
    }

    private Request createRequest() {
        RequestBody requestBody = initArgs();

        return new Request.Builder()
                .url(supplierConfig.getCreateUrl())
                .header("Content-Type", "application/x-www-form-urlencoded")
//                .header("x-client-id", config.getAppId())
//                .header("x-client-secret", config.getAppSecret())
                .post(requestBody)
                .build();
    }

    private Map<String, Object> initPayerInfo() {
        Map<String, Object> args = new HashMap<>(8);
//        args.put("customerName", "abc");
//        args.put("customerEmail", "abc@gmail.com");
//        args.put("customerPhone", "123456789");
//        return args;

        IndianBankCard card = indianCustomerEpi.findContact(request.getPayerId(), request.getPayeeId());
        if (card == null) {
            throw new InvalidPayConfigException("lack of customer info(name,email,mobile)");
        }

        if (StringUtil.notBlank(card.getAadhaarName())) {
            args.put("customerName", card.getAadhaarName());
        } else {
            args.put("customerName", "");
        }

        if (StringUtil.notBlank(card.getEmail())) {
            args.put("customerEmail", card.getEmail());
        } else {
            args.put("customerEmail", "");
        }

        if (StringUtil.notBlank(card.getMobile())) {
            args.put("customerPhone", card.getMobile());
        } else {
            args.put("customerPhone", "");
        }

        return args;
    }

    private RequestBody initArgs() {
        Map<String, Object> args = initPayerInfo();

        return new FormBody.Builder()
                .add("appId", supplierConfig.getAppId())
                .add("secretKey", supplierConfig.getAppSecret())
                .add("orderId", payment.getPaymentNo())
                .add("orderAmount", getAmount().toString())
                .add("orderCurrency", "INR")
                .add("customerName", (String) args.get("customerName"))
                .add("customerEmail", (String) args.get("customerEmail"))
                .add("customerPhone", (String) args.get("customerPhone"))
                .add("returnUrl", supplierConfig.getReturnUrl())
                .add("notifyUrl", supplierConfig.getNotifyUrl())
                .build();
    }

    private BigDecimal getAmount() {
        BigDecimal amount = request.getAmount();
        amount = DecimalUtil.scale(amount, 2);

        return amount;
    }
}
