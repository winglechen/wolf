package study.daydayup.wolf.business.pay.biz.service.india.razorpay;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayRequestException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeResponse;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api.china
 *
 * @author Wingle
 * @since 2020/2/27 7:37 下午
 **/
@Component
public class RazorpayServiceImpl implements RazorpayService {
    @Resource
    private RazorCreator razorCreator;
    @Resource
    private RazorPayer razorPayer;
    @Resource
    private RazorSubscriber razorSubscriber;
    @Resource
    private RazorPayout razorPayout;

    @Override
    public Result<PaymentCreateResponse> create(@Validated PaymentCreateRequest request) {
        request.setPaymentChannel(PaymentChannelEnum.RAZORPAY.getCode());

        PaymentCreateResponse response = razorCreator.create(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayVerifyResponse> verify(@NonNull PayVerifyRequest request) {
        request.setPaymentMethod(PaymentChannelEnum.RAZORPAY.getCode());

        PayVerifyResponse response = razorPayer.pay(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayoutResponse> payout(@NonNull PayoutRequest request) {
        request.setPaymentMethod(PaymentChannelEnum.RAZORPAY_PAYOUT.getCode());
        PayoutResponse response = razorPayout.payout(request);

        return Result.ok(response);
    }

    @Override
    public Result<SubscribeResponse> subscribe(@Validated SubscribeRequest request) {
        Object eventId = request.getHeader().get("eventId");
        Object signature = request.getHeader().get("signature");

        if (null == eventId || null == signature || null == request.getData()) {
            throw  new InvalidPayRequestException("Razorpay eventId、signature、data can't be null");
        }

        int code = razorSubscriber.subscribe((String) eventId, (String) signature, request.getData());
        SubscribeResponse response = SubscribeResponse.builder()
                .code(code)
                .build();
        return Result.ok(response);
    }
}
