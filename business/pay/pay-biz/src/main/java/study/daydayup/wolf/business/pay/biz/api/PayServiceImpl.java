package study.daydayup.wolf.business.pay.biz.api;

import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.domain.exception.InvalidPayRequestException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.business.pay.api.service.PayoutService;
import study.daydayup.wolf.business.pay.biz.domain.factory.PayServiceFactory;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/4/26 6:25 下午
 **/
@RpcService(protocol = "dubbo")
public class PayServiceImpl implements PayService, PayoutService {
    @Resource
    private PayServiceFactory factory;

    @Override
    public Result<PaymentCreateResponse> create(@Validated PaymentCreateRequest request) {
        if (null == request.getPaymentMethod()) {
            throw new InvalidPayRequestException("PaymentMethod can't be null");
        }

        PayService service = factory.create(request.getPaymentMethod());
        return service.create(request);
    }

    @Override
    public Result<PayVerifyResponse> verify(PayVerifyRequest request) {
        if (null == request.getPaymentMethod()) {
            throw new InvalidPayRequestException("PaymentMethod can't be null");
        }

        PayService service = factory.create(request.getPaymentMethod());
        return service.verify(request);
    }

    @Override
    public Result<PayoutResponse> payout(PayoutRequest request) {
        if (null == request.getPaymentMethod()) {
            throw new InvalidPayRequestException("PaymentMethod can't be null");
        }

        return null;
    }

}
