package study.daydayup.wolf.business.pay.biz.service.india.onionpay;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.PaymentCreateFailException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentCreator;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.onionpay
 *
 * @author Wingle
 * @since 2020/7/2 4:10 下午
 **/
@Slf4j
@Component
public class OnionPayCreator extends AbstractPaymentCreator implements PaymentCreator {
    private static final String CONFIG_KEY = "onionPay";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void initPayment(boolean duplicateCheck) {
    }

    @Override
    public void callPayEpi() {
    }

    @Override
    public void logCreateResponse(String data) {
    }

    @Override
    public void parseCreateResponse() {

    }

    @Override
    public void savePayment() {
    }

    @Override
    public PaymentCreateResponse formatResponse() {
        initConfig(CONFIG_KEY);

        String token = storeCreateRequest();
        setResponseAttachment(token);

        return createPaymentCreateResponse();
    }

    private String storeCreateRequest() {
        createRequest.setCurrencyCode("INR");

        String json = JSON.toJSONString(createRequest);
        String key;
        Boolean status;

        for (int i = 0; i < 3; i++) {
            key  = StringUtil.uuid();
            status = stringRedisTemplate.opsForValue().setIfAbsent(key, json, Duration.ofMinutes(15));
            if (null != status && status.equals(true)) {
                return key;
            }
        }

        throw new PaymentCreateFailException("create payment token fail");
    }

    private void setResponseAttachment(String token) {
        String createUrl = supplierConfig.getCreateUrl();
        createUrl = createUrl.replace("{token}", token);

        attachment.put("payUrl", createUrl);
        attachment.put("amount", getAmount());
        attachment.put("currencyCode", "INR");
    }

    private PaymentCreateResponse createPaymentCreateResponse() {
        PaymentCreateResponse response = new PaymentCreateResponse();
        response.setPaymentNo(null);
        response.setAmount(getAmount());
        response.setPaymentChannel(PaymentChannelEnum.DLOCAL.getCode());
        response.setPayArgs(attachment.getMap());

        return response;
    }








}
