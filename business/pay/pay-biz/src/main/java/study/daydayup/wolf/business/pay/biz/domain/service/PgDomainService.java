package study.daydayup.wolf.business.pay.biz.domain.service;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayRequestException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeResponse;
import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.business.pay.biz.domain.factory.PayServiceFactory;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/4/26 6:25 下午
 **/
@Component
public class PgDomainService implements PayService {
    @Resource
    private PayServiceFactory payServiceFactory;

    @Override
    public Result<PaymentCreateResponse> create(@Validated PaymentCreateRequest request) {
        paymentChannelWithList(request);
        if (null == request.getPaymentChannel()) {
            throw new InvalidPayRequestException("PaymentMethod can't be null");
        }

        PayService service = payServiceFactory.create(request.getPaymentChannel());
        return service.create(request);
    }

    private void paymentChannelWithList(@NonNull PaymentCreateRequest request) {
        if (BeanUtil.equals(PaymentChannelEnum.DLOCAL.getCode(), request.getPaymentChannel())) {
            return;
        }

        request.setPaymentChannel(PaymentChannelEnum.ONIONPAY.getCode());
//        request.setPaymentChannel(PaymentChannelEnum.DOKYPAY.getCode());
        return;

//        Long payerId = request.getPayerId();
//        if (payerId == null) {
//            return;
//        }
//
//        if (payerId % 10 == 3) {
//            request.setPaymentMethod(PaymentChannelEnum.CASHFREE.getCode());
//        }
    }

    @Override
    public Result<PayVerifyResponse> verify(PayVerifyRequest request) {
        if (null == request.getPaymentMethod()) {
            throw new InvalidPayRequestException("PaymentMethod can't be null");
        }

        PayService service = payServiceFactory.create(request.getPaymentMethod());
        return service.verify(request);
    }

    @Override
    public Result<SubscribeResponse> subscribe(@Validated SubscribeRequest request) {
        if (null == request.getPaymentMethod()) {
            throw new InvalidPayRequestException("PaymentMethod can't be null");
        }

        PayService service = payServiceFactory.create(request.getPaymentMethod());
        return service.subscribe(request);
    }


}
