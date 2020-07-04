package study.daydayup.wolf.business.pay.biz.service.india.dlocal;

import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.entity.PayNotification;
import study.daydayup.wolf.business.pay.api.domain.enums.NotifyReturnEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.service.india.dlocal.handler.DLocalPaidHandler;
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
 * study.daydayup.wolf.business.pay.biz.service.india.dlocal
 *
 * @author Wingle
 * @since 2020/7/4 5:28 下午
 **/
@Slf4j
@Component
public class DLocalSubscriber extends AbstractPaymentSubscriber implements PaymentSubscriber {
    private static final int LOG_TYPE = PaymentLogTypeEnum.PAY_RETURN.getCode();
    private static final int PAYMENT_METHOD = PaymentChannelEnum.DLOCAL.getCode();
    private static final String CONFIG_KEY = "dLocal";

    private JSONObject response;
    private SubscribeRequest request;

    @Resource
    private DLocalPaidHandler dLocalPaidHandler;

    public int subscribe(@NonNull SubscribeRequest request) {
        this.request = request;
        logSubscribeResponse(LOG_TYPE, PAYMENT_METHOD, request.getPayData());

        if (!parseResponse(request.getData())) {
            return NotifyReturnEnum.PARSE_ERROR.getCode();
        }

        loadPayment(paymentNo);
        initConfig(CONFIG_KEY, payment.getPayeeId());

        if (!isResponseValid()) {
            return NotifyReturnEnum.INVALID_SIGN.getCode();
        }

        return handlePayment();
    }

    private int handlePayment() {
        PayNotification notification = PayNotification.builder()
                .payment(payment)
                .amount(getAmount())
                .paymentNo(response.getString("order_id"))
                .outOrderNo(payment.getOutTradeNo())
                .status(response.getString("status"))
                .outPaidAt(DateUtil.asLocalDateTime(response.getString("approved_date")))
                .paymentMethod(PAYMENT_METHOD)
                .build();

        return dLocalPaidHandler.handle(notification);
    }

    private boolean parseResponse(@NonNull String data) {
        Map<String, String> map = URLUtil.parseQuery(data);
        if (MapUtil.isEmpty(map)) {
            return false;
        }

        Map<String, Object> objMap = new HashMap<>(map); response = new JSONObject(objMap);
        return isResponseSuccess(response);
    }

    private boolean isResponseSuccess(JSONObject json) {
        if (null == json) {
            return false;
        }

        if (json.getString("order_id") == null) {
            return false;
        }
        paymentNo = json.getString("order_id");

        String status = json.getString("status");
        return null != status;
    }

    private boolean isResponseValid(){
        String responseSign = request.getHeader().get("Authorization");
        String xDate = request.getHeader().get("X-Date");
        if (StringUtil.isBlank(responseSign) || StringUtil.isBlank(xDate)) {
            return false;
        }

        String sign = SignUtil.create(supplierConfig.getAppSecret(), supplierConfig.getAppName(), xDate, request.getData());
        return responseSign.equals(sign);
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
