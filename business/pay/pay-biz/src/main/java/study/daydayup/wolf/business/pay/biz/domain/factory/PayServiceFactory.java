package study.daydayup.wolf.business.pay.biz.domain.factory;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.PayServiceNotFoundException;
import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.business.pay.biz.service.india.dlocal.DLocalService;
import study.daydayup.wolf.business.pay.biz.service.india.onionpay.OnionPayService;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorpayService;
import study.daydayup.wolf.business.pay.biz.service.india.cashfree.CashfreeService;
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
    private CashfreeService cashFreeService;
    @Resource
    private DokypayService dokypayService;
    @Resource
    private RazorpayService razorpayService;
    @Resource
    private DLocalService dLocalService;
    @Resource
    private OnionPayService onionPayService;


    public PayService create(int paymentCode) {
        PaymentChannelEnum paymentMethod = EnumUtil.codeOf(paymentCode, PaymentChannelEnum.class);

        switch (paymentMethod) {
            case CASHFREE:
                return cashFreeService;
            case DOKYPAY:
                return dokypayService;
            case RAZORPAY:
                return razorpayService;
            case DLOCAL:
                return dLocalService;
            case ONIONPAY:
                return onionPayService;
            default:
                throw new PayServiceNotFoundException();
        }

    }
}
