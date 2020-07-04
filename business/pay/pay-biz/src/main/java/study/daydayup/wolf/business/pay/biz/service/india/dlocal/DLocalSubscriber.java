package study.daydayup.wolf.business.pay.biz.service.india.dlocal;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentSubscriber;

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
}
