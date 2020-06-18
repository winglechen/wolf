package study.daydayup.wolf.business.pay.biz.service.india.cashfree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.api.domain.exception.epi.InvalidEpiResponseException;
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
public class CashfreeRnCreator extends AbstractPaymentCreator implements PaymentCreator {
    private static final String CONFIG_KEY = "cashfree";
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

    private PaySupplier config;

    @Resource
    private PayConfig payConfig;
    @Resource
    private IndianCustomerEpi indianCustomerEpi;

    @Override
    public void callPayEpi() {
        initConfig();
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

    private void initPayerInfo() {
//        args.put("payerName", "ppea");
//        args.put("payerEmail", "abc@gmail.com");
//        args.put("payerMobile", "123456789");
//        return;

        IndianBankCard card = indianCustomerEpi.findContact(createRequest.getPayerId(), createRequest.getPayeeId());
        if (card == null) {
            return;
        }

        if (StringUtil.notBlank(card.getAadhaarName())) {
            attachment.put("customerName", card.getAadhaarName());
        }

        if (StringUtil.notBlank(card.getEmail())) {
            attachment.put("customerEmail", card.getEmail());
        }

        if (StringUtil.notBlank(card.getMobile())) {
            attachment.put("customerPhone", card.getMobile());
        }
    }

    private void setResponseAttachment(@NonNull JSONObject data) {
        initPayerInfo();

        attachment.put("cftoken", data.getString("cftoken"));
        attachment.put("appId", config.getAppId());
        attachment.put("orderId", payment.getPaymentNo());
        attachment.put("orderAmount", getAmount());
        attachment.put("orderCurrency", "INR");
        attachment.put("notifyUrl", config.getNotifyUrl());
    }

    private boolean isResponseSuccess(JSONObject json) {
        if (null == json) {
            return false;
        }

        if (StringUtil.isBlank(json.getString("cftoken"))) {
            return false;
        }

        return "OK".equalsIgnoreCase(json.getString("resultCode"));
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

    private void initConfig() {
        config = payConfig.getSupplier().get(CONFIG_KEY);
    }

    private Request createRequest() {
        String args = initArgs();
        RequestBody requestBody = RequestBody.create(args, JSON_CONTENT_TYPE);

        return new Request.Builder()
                .url(config.getCreateUrl())
                .header("Content-Type", "application/json")
                .header("x-client-id", config.getAppId())
                .header("x-client-secret", config.getAppSecret())

                .post(requestBody)
                .build();
    }

    private String initArgs() {
        Map<String, Object> args = new HashMap<>(8);
        args.put("orderId", payment.getPaymentNo());
        args.put("orderAmount", getAmount());
        args.put("orderCurrency", "INR");

        return JSON.toJSONString(args);
    }

    private BigDecimal getAmount() {
        BigDecimal amount = createRequest.getAmount();
        amount = DecimalUtil.scale(amount, 2);

        return amount;
    }
}
