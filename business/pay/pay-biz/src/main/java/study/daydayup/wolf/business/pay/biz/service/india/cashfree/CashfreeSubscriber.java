package study.daydayup.wolf.business.pay.biz.service.india.cashfree;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.biz.domain.service.AbstractPaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.domain.service.PaymentSubscriber;
import study.daydayup.wolf.business.pay.biz.service.india.dokypay.handler.DokypayPaidHandler;

import javax.annotation.Resource;

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
    private static final int PAYMENT_METHOD = PaymentChannelEnum.CASEFREE.getCode();
    private static final String CONFIG_KEY = "cashfree";

    private PaySupplier config;
    @Resource
    private PayConfig payConfig;
    @Resource
    private DokypayPaidHandler paidHandler;
}
