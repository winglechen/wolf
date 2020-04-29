package study.daydayup.wolf.business.pay.biz.domain.factory;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.PayServiceNotFoundException;
import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorpayService;
import study.daydayup.wolf.business.pay.biz.service.india.cashfree.CashFreeService;
import study.daydayup.wolf.business.pay.biz.service.india.dokypay.DokypayService;
import study.daydayup.wolf.common.util.lang.EnumUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.util
 *
 * @author Wingle
 * @since 2020/4/26 6:36 下午
 **/
@Component
public class PayServiceFactory {
    @Resource
    private CashFreeService cashFreeService;
    @Resource
    private DokypayService dokypayService;
    @Resource
    private RazorpayService razorpayService;


    public PayService create(int paymentCode) {
        PaymentMethodEnum paymentMethod = EnumUtil.codeOf(paymentCode, PaymentMethodEnum.class);

        switch (paymentMethod) {
            case CASEFREE:
                return cashFreeService;
            case DOKYPAY:
                return dokypayService;
            case RAZORPAY:
                return razorpayService;
            default:
                throw new PayServiceNotFoundException();
        }

    }
}
