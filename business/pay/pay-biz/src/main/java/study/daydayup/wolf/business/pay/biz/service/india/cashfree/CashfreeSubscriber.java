package study.daydayup.wolf.business.pay.biz.service.india.cashfree;

import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.api.domain.entity.PayNotification;
import study.daydayup.wolf.business.pay.api.domain.enums.NotifyReturnEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.service.india.cashfree.handler.CashfreePaidHandler;
import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.DecimalUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.common.util.net.URLUtil;
import study.daydayup.wolf.common.util.time.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.cashfree
 *
 * @author Wingle
 * @since 2020/6/8 2:56 下午
 **/
@Slf4j
@Component
public class CashfreeSubscriber extends AbstractPaymentSubscriber implements PaymentSubscriber {
    private static final int LOG_TYPE = PaymentLogTypeEnum.PAY_RETURN.getCode();
    private static final int PAYMENT_METHOD = PaymentChannelEnum.CASHFREE.getCode();
    private static final String CONFIG_KEY = "cashfree";

    private JSONObject response;
    private PaySupplier config;
    @Resource
    private PayConfig payConfig;
    @Resource
    private CashfreePaidHandler cashfreePaidHandler;

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

    private void initConfig() {
        config = payConfig.getSupplier().get(CONFIG_KEY);
    }

    private boolean parseResponse(@NonNull String data) {
        Map<String, String> map = URLUtil.parseQuery(data);
        if (MapUtil.isEmpty(map)) {
            return false;
        }

        Map<String, Object> objMap = new HashMap<>(map);
        response = new JSONObject(objMap);

        return isResponseSuccess(response);
    }

    private boolean isResponseSuccess(JSONObject json) {
        if (null == json) {
            return false;
        }

        String status = json.getString("txStatus");
        return null != status;
    }

    private boolean isResponseValid(){
        String responseSign = response.getString("signature");
        if (StringUtil.isBlank(responseSign)) {
            return false;
        }

        Map<String, Object> data = response.getInnerMap();
        String sign = SignUtil.create(config.getAppSecret(), data);

        return responseSign.equals(sign);
    }

    private int savePayment() {
        PayNotification notification = PayNotification.builder()
                .amount(getAmount())
                .paymentNo(response.getString("orderId"))
                .outOrderNo(response.getString("referenceId"))
                .status(response.getString("txStatus"))
                .outPaidAt(DateUtil.asLocalDateTime(response.getString("txTime")))
                .paymentMethod(PAYMENT_METHOD)
                .build();

        return cashfreePaidHandler.handle(notification);
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