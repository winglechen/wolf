package study.daydayup.wolf.business.pay.biz.service.india.dokypay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.api.domain.enums.NotifyReturnEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.service.india.dokypay.util.SignUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
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
    private JSONObject response;
    private PaySupplier config;

    @Resource
    private PayConfig payConfig;

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
        response = JSON.parseObject(data);

        return isResponseSuccess(response);
    }

    private boolean isResponseSuccess(JSONObject json) {
        if (null == json) {
            return false;
        }

        JSONObject data = json.getJSONObject("data");
        if (null == data) {
            return false;
        }

        if ("0000".equals(data.getString("resultCode"))) {
            return false;
        }

        return "success".equals(data.getString("transStatus"));
    }

    private boolean isResponseValid(){
        String responseSign = response.getJSONObject("data").getString("sign");
        if (StringUtil.isBlank(responseSign)) {
            return false;
        }

        Map<String, Object> data = response.getJSONObject("data").getInnerMap();
        String sign = SignUtil.create(config.getAppSecret(), data);

        return responseSign.equals(sign);
    }

    private void initConfig() {

    }

    private int savePayment() {
        return 0;
    }


}
