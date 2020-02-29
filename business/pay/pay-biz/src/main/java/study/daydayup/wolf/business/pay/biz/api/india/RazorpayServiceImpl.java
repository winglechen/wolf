package study.daydayup.wolf.business.pay.biz.api.india;

import lombok.NonNull;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.api.service.india.RazorpayService;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorCreator;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorPayer;
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

    @Override
    public Result<PaymentCreateResponse> create(@NonNull PaymentCreateRequest request) {
        request.setPaymentMethod(PaymentMethodEnum.RAZORPAY.getCode());

        PaymentCreateResponse response = creator.create(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayResponse> pay(@NonNull PayRequest request) {
        request.setPaymentMethod(PaymentMethodEnum.RAZORPAY.getCode());

        PayResponse response = payer.pay(request);
        return Result.ok(response);
    }

    @Override
    public Result<String> subscribe(@NonNull String data) {
        String response = subscriber.subscribe(data);
        return Result.ok(response);
    }
}
