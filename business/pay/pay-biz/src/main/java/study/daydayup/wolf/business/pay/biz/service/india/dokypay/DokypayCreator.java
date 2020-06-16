package study.daydayup.wolf.business.pay.biz.service.india.dokypay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.exception.epi.InvalidEpiResponseException;
import study.daydayup.wolf.business.pay.api.dto.india.IndianBankCard;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentCreator;
import study.daydayup.wolf.business.pay.biz.epi.india.IndianCustomerEpi;
import study.daydayup.wolf.business.pay.biz.service.india.dokypay.util.SignUtil;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.dokypay
 *
 * @author Wingle
 * @since 2020/4/28 9:37 下午
 **/
@Slf4j
@Component
public class DokypayCreator extends AbstractPaymentCreator implements PaymentCreator {
    private static final String CONFIG_KEY = "dokypay";
    private static final String PROD_NAME = "southeast.asia";

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
            log.error("Dokypay create exception ", e);
            throw new InvalidEpiResponseException("Dokypay creator call epi failed");
        }
    }

    private void parseResponse(Response response) {
        if (null == response || !response.isSuccessful()) {
            throw new InvalidEpiResponseException("Dokypay create response is invalid");
        }

        try {
            apiResponse = Objects.requireNonNull(response.body()).string();
            log.info("dokypay create response: {}", apiResponse);
        } catch (Exception e) {
            throw new InvalidEpiResponseException("Dokypay create responseBody is invalid");
        }
    }

    @Override
    public void parseEpiResponse() {
        JSONObject json = JSON.parseObject(apiResponse);
        if (!isResponseSuccess(json)) {
            throw new InvalidEpiResponseException("Dokypay create response parse error");
        }

        if (!isResponseValid(json)) {
            throw new InvalidEpiResponseException("Dokypay create response invalid sign");
        }

        JSONObject data = json.getJSONObject("data");
        setPaymentAttachment(data);
        setResponseAttachment(data);
    }

    private void setPaymentAttachment(@NonNull JSONObject data) {
        payment.setOutTradeNo(data.getString("tradeNo"));
    }

    private void setResponseAttachment(@NonNull JSONObject data) {
        attachment.set("payUrl", data.getString("url"));
    }

    /**
     * @param json json request
     * @return boolean response is value
     */
    private boolean isResponseValid(JSONObject json) {
        String responseSign = json.getJSONObject("data").getString("sign");
        if (StringUtil.isBlank(responseSign)) {
            return false;
        }

        Map<String, Object> data = json.getJSONObject("data").getInnerMap();
        String sign = SignUtil.create(config.getAppSecret(), data);

        return responseSign.equals(sign);
    }

    private boolean isResponseSuccess(JSONObject json) {
        if (null == json) {
            return false;
        }

        JSONObject data = json.getJSONObject("data");
        if (null == data) {
            return false;
        }

        if (StringUtil.isBlank(data.getString("url"))) {
            return false;
        }

        return "0000".equals(data.getString("resultCode"));
    }

    private Request createRequest() {
        String args = initArgs();
        RequestBody requestBody = RequestBody.create(args, JSON_CONTENT_TYPE);

        return new Request.Builder()
                .url(config.getCreateUrl())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();
    }

    private String initArgs() {
        Map<String, Object> args = new HashMap<>(8);
        args.put("appId", config.getAppId());
        args.put("prodName", PROD_NAME);
        args.put("version", config.getVersion());
        args.put("returnUrl", config.getReturnUrl());
        args.put("notifyUrl", config.getNotifyUrl());

        args.put("merTransNo", payment.getPaymentNo());
        args.put("country", "IN");
        args.put("currency", "INR");
        args.put("amount", getAmount());
        initPayerInfo(args);

        Map<String, Object> extInfo = new HashMap<>(2);
        extInfo.put("paymentTypes","credit,debit,ewallet,upi");
        args.put("extInfo", extInfo);


        String sign = SignUtil.create(config.getAppSecret(), args);
        args.put("sign", sign);

        return JSON.toJSONString(args);
    }

    private void initPayerInfo(@NonNull Map<String, Object> args) {
//        args.put("payerName", "ppea");
//        args.put("payerEmail", "abc@gmail.com");
//        args.put("payerMobile", "123456789");
//        return;

        IndianBankCard card = indianCustomerEpi.findContact(request.getPayerId(), request.getPayeeId());
        if (card == null) {
            return;
        }

        if (StringUtil.notBlank(card.getAadhaarName())) {
            args.put("payerName", card.getAadhaarName());
        }

        if (StringUtil.notBlank(card.getEmail())) {
            args.put("payerEmail", card.getEmail());
        }

        if (StringUtil.notBlank(card.getMobile())) {
            args.put("payerMobile", card.getMobile());
        }
    }

    private String getAmount() {
        BigDecimal amount = request.getAmount();
        amount = DecimalUtil.scale(amount, 2);

        return amount.toString();
    }
}
