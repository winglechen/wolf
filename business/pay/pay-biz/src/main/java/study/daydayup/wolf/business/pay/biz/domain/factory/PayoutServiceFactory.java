package study.daydayup.wolf.business.pay.biz.domain.factory;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.payout.PayoutServiceNotFoundException;
import study.daydayup.wolf.business.pay.api.service.PayoutService;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorpayService;
import study.daydayup.wolf.business.pay.biz.service.india.cashfree.CashFreeService;
import study.daydayup.wolf.business.pay.biz.service.india.dokypay.DokypayService;
import study.daydayup.wolf.common.util.lang.EnumUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.domain.factory
 *
 * @author Wingle
 * @since 2020/4/29 6:25 下午
 **/
@Component
public class PayoutServiceFactory {
    @Resource
    private CashFreeService cashFreeService;
    @Resource
    private DokypayService dokypayService;
    @Resource
    private RazorpayService razorpayService;

    public PayoutService create(int paymentCode) {
        PaymentChannelEnum paymentMethod = EnumUtil.codeOf(paymentCode, PaymentChannelEnum.class);

        switch (paymentMethod) {
            case CASEFREE:
                return cashFreeService;
            case DOKYPAY:
                return dokypayService;
            case RAZORPAY:
                return razorpayService;
            default:
                throw new PayoutServiceNotFoundException();
        }
    }
}
