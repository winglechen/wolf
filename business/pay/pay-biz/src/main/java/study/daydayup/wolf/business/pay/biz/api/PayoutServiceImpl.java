package study.daydayup.wolf.business.pay.biz.api;

import study.daydayup.wolf.business.pay.api.domain.exception.pay.InvalidPayRequestException;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.service.PayoutService;
import study.daydayup.wolf.business.pay.biz.domain.factory.PayoutServiceFactory;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/4/29 6:24 下午
 **/
@RpcService(protocol = "dubbo")
public class PayoutServiceImpl implements PayoutService {
    @Resource
    private PayoutServiceFactory factory;

    @Override
    public Result<PayoutResponse> payout(PayoutRequest request) {
        if (null == request.getPaymentMethod()) {
            throw new InvalidPayRequestException("PaymentMethod can't be null");
        }

        PayoutService service = factory.create(request.getPaymentMethod());
        return service.payout(request);
    }
}
