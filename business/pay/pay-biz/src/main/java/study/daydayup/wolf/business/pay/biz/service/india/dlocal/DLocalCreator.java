package study.daydayup.wolf.business.pay.biz.service.india.dlocal;

import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;
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

    }

    @Override
    public void parseCreateResponse() {

    }

    private Request createRequest() {
        String args = initArgs();
        RequestBody requestBody = RequestBody.create(args, JSON_CONTENT_TYPE);

        return new Request.Builder()
                .url(supplierConfig.getCreateUrl())
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build();
    }

    private String initArgs() {
        Map<String, Object> args = new HashMap<>(8);
        args.put("appId", supplierConfig.getAppId());
        args.put("version", supplierConfig.getVersion());
        args.put("returnUrl", supplierConfig.getReturnUrl());
        args.put("notifyUrl", supplierConfig.getNotifyUrl());

        args.put("merTransNo", payment.getPaymentNo());
        args.put("country", "IN");
        args.put("currency", "INR");
        args.put("amount", getAmount());
        initPayerInfo(args);

        Map<String, Object> extInfo = new HashMap<>(2);
        extInfo.put("paymentTypes","credit,debit,ewallet,upi");
        args.put("extInfo", extInfo);


        String sign = SignUtil.create(supplierConfig.getAppSecret(), args);
        args.put("sign", sign);

        return JSON.toJSONString(args);
    }

    private void initPayerInfo(@NonNull Map<String, Object> args) {
//        args.put("payerName", "ppea");
//        args.put("payerEmail", "abc@gmail.com");
//        args.put("payerMobile", "123456789");
//        return;

        IndianBankCard card = indianCustomerEpi.findContact(createRequest.getPayerId(), createRequest.getPayeeId());
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

}
