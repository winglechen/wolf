package study.daydayup.wolf.business.pay.biz.api.india;

import lombok.NonNull;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.service.india.RazorpayService;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorCreator;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorPayer;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorPayout;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorSubscriber;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api.china
 *
 * @author Wingle
 * @since 2020/2/27 7:37 下午
 **/
@RpcService(protocol = "dubbo")
public class RazorpayServiceImpl implements RazorpayService {
    @Resource
    private RazorCreator creator;
    @Resource
    private RazorPayer payer;
    @Resource
    private RazorSubscriber subscriber;
    @Resource
    private RazorPayout payout;

    @Override
    public Result<PaymentCreateResponse> create(@Validated PaymentCreateRequest request) {
        request.setPaymentMethod(PaymentMethodEnum.RAZORPAY.getCode());

        PaymentCreateResponse response = creator.create(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayVerifyResponse> verify(@NonNull PayVerifyRequest request) {
        request.setPaymentMethod(PaymentMethodEnum.RAZORPAY.getCode());

        PayVerifyResponse response = payer.pay(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayoutResponse> payout(@NonNull PayoutRequest request) {
        request.setPaymentMethod(PaymentMethodEnum.RAZORPAY_PAYOUT.getCode());
        PayoutResponse response = payout.payout(request);

        return Result.ok(response);
    }

    @Override
    public Result<Integer> subscribe(@NonNull String eventId, @NonNull String signature, @NonNull String data) {
        int response = subscriber.subscribe(eventId, signature, data);
        return Result.ok(response);
    }
}
