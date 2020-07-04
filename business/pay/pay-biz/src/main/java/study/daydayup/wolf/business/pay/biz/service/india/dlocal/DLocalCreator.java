package study.daydayup.wolf.business.pay.biz.service.india.dlocal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.exception.epi.InvalidEpiResponseException;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.PaymentCreateFailException;
import study.daydayup.wolf.business.pay.api.dto.india.IndianBankCard;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentCreator;
import study.daydayup.wolf.business.pay.biz.epi.india.IndianCustomerEpi;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.dlocal
 *
 * @author Wingle
 * @since 2020/6/28 6:37 下午
 **/
@Slf4j
@Component
public class DLocalCreator extends AbstractPaymentCreator implements PaymentCreator {
    private static final String DEFAULT_DOCUMENT = "aaaaa1111a";
    private static final String CONFIG_KEY = "dLocal";

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

    @Resource
    private IndianCustomerEpi indianCustomerEpi;

    @Override
    public void callPayEpi() {
        initConfig(CONFIG_KEY, createRequest.getPayeeId());
        Request payRequest = createRequest();

        try {
            Response response = CLIENT.newCall(payRequest).execute();
            parseResponse(response);
        } catch (Exception e) {
            log.error("dLocal create exception ", e);
            throw new InvalidEpiResponseException("dLocal creator call epi failed");
        }
    }

    @Override
    public void parseCreateResponse() {
        JSONObject json = JSON.parseObject(apiResponse);

        if (!isResponseSuccess(json)) {
            throw new InvalidEpiResponseException("dLocal create response parse error");
        }

        updateOutTradeNo(json);
        setResponseAttachment(json);
    }

    private void updateOutTradeNo(@NonNull JSONObject data) {
        payment.setOutTradeNo(data.getString("id"));
    }

    private void setResponseAttachment(@NonNull JSONObject data) {
        attachment.put("payUrl", data.getString("redirect_url"));
        attachment.put("paymentNo", payment.getPaymentNo());
        attachment.put("amount", getAmount());
        attachment.put("currencyCode", "INR");
    }

    private void parseResponse(Response response) {
        if (null == response || !response.isSuccessful()) {
            throw new InvalidEpiResponseException("dLocal create response is invalid");
        }

        try {
            apiResponse = Objects.requireNonNull(response.body()).string();
            log.info("dLocal create response: {}", apiResponse);
        } catch (Exception e) {
            throw new InvalidEpiResponseException("dLocal create responseBody is invalid");
        }
    }

    private boolean isResponseSuccess(JSONObject json) {
        if (null == json) {
            return false;
        }

        if (StringUtil.isBlank(json.getString("redirect_url"))) {
            return false;
        }

        return "100".equalsIgnoreCase(json.getString("status_code"));
    }

    private Request createRequest() {
        String args = initArgs();

        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        String xDate = now.toString();

        String sign = SignUtil.create(supplierConfig.getAppSecret(), supplierConfig.getAppName(), xDate, args);

        RequestBody requestBody = RequestBody.create(args, JSON_CONTENT_TYPE);
        return new Request.Builder()
                .url(supplierConfig.getCreateUrl())
                .header("Content-Type", "application/json")
                .header("X-Date", xDate)
                .header("X-Login", supplierConfig.getAppName())
                .header("X-Trans-Key", supplierConfig.getAppId())
                .header("X-Version", "2.1")
                .header("User-Agent", "onionPay / 1.0")
                .header("Authorization", "V2-HMAC-SHA256, Signature: " + sign)
                .header("X-Idempotency-Key", payment.getPaymentNo())
                .post(requestBody)
                .build();
    }

    private String initArgs() {
        Map<String, Object> args = new HashMap<>(8);

        args.put("amount", getAmount());
        args.put("currency", "INR");
        args.put("country", "IN");
        args.put("payment_method_id", "IN");
        args.put("payment_method_flow", "IN");
        args.put("callback_url", supplierConfig.getReturnUrl());
        args.put("notification_url", supplierConfig.getNotifyUrl());
        args.put("order_id", payment.getPaymentNo());
        initPayerInfo(args);

        return JSON.toJSONString(args);
    }

    private void initPayerInfo(@NonNull Map<String, Object> args) {
        Map<String, Object> payer = new HashMap<>(4);
        IndianBankCard card = indianCustomerEpi.findContact(createRequest.getPayerId(), createRequest.getPayeeId());
        if (card == null) {
            throw new PaymentCreateFailException("customer info does exists for dlocal");
        }

        if (StringUtil.notBlank(card.getAadhaarName())) {
            payer.put("name", card.getAadhaarName());
        } else {
            payer.put("name", "");
        }

        if (StringUtil.notBlank(card.getEmail())) {
            payer.put("email", card.getEmail());
        } else {
            payer.put("name", "");
        }

        if (StringUtil.notBlank(card.getMobile())) {
            payer.put("phone", card.getMobile());
        } else {
            payer.put("phone", "");
        }

        payer.put("document", DEFAULT_DOCUMENT);
        args.put("payer", payer);
    }

}
