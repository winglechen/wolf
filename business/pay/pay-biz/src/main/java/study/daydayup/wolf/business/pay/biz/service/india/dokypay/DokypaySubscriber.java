package study.daydayup.wolf.business.pay.biz.service.india.dokypay;

import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.api.domain.entity.PayNotification;
import study.daydayup.wolf.business.pay.api.domain.enums.NotifyReturnEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.service.india.dokypay.handler.DokypayPaidHandler;
import study.daydayup.wolf.business.pay.biz.service.india.dokypay.util.SignUtil;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.common.util.lang.UrlUtil;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.dokypay
 *
 * @author Wingle
 * @since 2020/4/29 6:58 下午
 **/
@Slf4j
@Component
public class DokypaySubscriber extends AbstractPaymentSubscriber implements PaymentSubscriber {
    private static final int LOG_TYPE = PaymentLogTypeEnum.PAY_RETURN.getCode();
    private static final int PAYMENT_METHOD = PaymentMethodEnum.DOKYPAY.getCode();
    private static final String CONFIG_KEY = "dokypay";
    private JSONObject response;
    private PaySupplier config;

    @Resource
    private PayConfig payConfig;
    @Resource
    private DokypayPaidHandler paidHandler;

    public int subscribe(@NonNull String data) {
        logResponse(LOG_TYPE, PAYMENT_METHOD, data);
        initConfig();

        if (!parseResponse(data)) {
            return NotifyReturnEnum.PARSE_ERROR.getCode();
        }

        if (!isResponseValid()) {
            return NotifyReturnEnum.INVALID_SIGN.getCode();
        }

        return savePayment();
    }

    private boolean parseResponse(@NonNull String data) {
        try {
            Map<String, String> map = UrlUtil.parseUrl(data);
            Map<String, Object> objMap = new HashMap<>(map);
            response = new JSONObject(objMap);
        } catch (UnsupportedEncodingException e) {
            return false;
        }
        return isResponseSuccess(response);
    }

    private boolean isResponseSuccess(JSONObject json) {
        if (null == json) {
            return false;
        }

        JSONObject data = json.getJSONObject("transStatus");
        return null != data;
    }

    private boolean isResponseValid(){
        String responseSign = response.getString("sign");
        if (StringUtil.isBlank(responseSign)) {
            return false;
        }

        Map<String, Object> data = response.getInnerMap();
        String sign = SignUtil.create(config.getAppSecret(), data);

        return responseSign.equals(sign);
    }

    private void initConfig() {
        config = payConfig.getSupplier().get(CONFIG_KEY);
    }

    private int savePayment() {
        PayNotification notification = PayNotification.builder()
                .amount(getAmount())
                .paymentNo(response.getString("merTransNo"))
                .outOrderNo(response.getString("transNo"))
                .status(response.getString("transStatus"))
                .paymentMethod(PAYMENT_METHOD)
                .build();

        return paidHandler.handle(notification);
    }

    private BigDecimal getAmount() {
        String strAmount = response.getString("amount");
        if (StringUtil.isBlank(strAmount)) {
            return BigDecimal.ZERO;
        }

        BigDecimal amount = new BigDecimal(strAmount);
        return DecimalUtil.scale(amount);
    }


}
