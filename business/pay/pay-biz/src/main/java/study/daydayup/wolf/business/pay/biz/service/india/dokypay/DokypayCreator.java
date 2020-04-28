package study.daydayup.wolf.business.pay.biz.service.india.dokypay;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentCreator;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentCreator;
import study.daydayup.wolf.business.pay.biz.service.india.dokypay.util.SignUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.dokypay
 *
 * @author Wingle
 * @since 2020/4/28 9:37 下午
 **/
@Component
public class DokypayCreator extends AbstractPaymentCreator implements PaymentCreator {
    private static String CONFIG_KEY = "dokypay";
    private static String PROD_NAME = "southeast.asia";
    private PaySupplier config;

    @Resource
    private PayConfig payConfig;


    @Override
    public void callPayEpi() {
        initConfig();

        Map<String, Object> request = initRequest();


    }

    @Override
    public void parseEpiResponse() {

    }

    private void initConfig() {

    }

    private Map<String, Object> initRequest() {
        Map<String, Object> request = new HashMap<>(8);
        request.put("appId", config.getAppId());
        request.put("prodName", PROD_NAME);
        request.put("version", config.getVersion());
        request.put("returnUrl", config.getReturnUrl());
        request.put("notifyUrl", config.getNotifyUrl());

        request.put("merTransNo", payment.getPaymentNo());
        request.put("country", "IN");
        request.put("currency", "INR");
        request.put("amount", getAmount());


        Map<String, Object> extInfo = new HashMap<>(2);
        extInfo.put("paymentTypes","credit,debit,ewallet,upi");
        request.put("extInfo", extInfo);

        String sign = SignUtil.create(config.getAppSecret(), request);

        return request;
    }

    private String getAmount() {
        return null;
    }
}
