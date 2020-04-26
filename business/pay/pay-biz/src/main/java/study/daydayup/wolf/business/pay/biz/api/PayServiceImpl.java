package study.daydayup.wolf.business.pay.biz.api;

import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

/**
 * study.daydayup.wolf.business.pay.biz.api
 *
 * @author Wingle
 * @since 2020/4/26 6:25 下午
 **/
@RpcService(protocol = "dubbo")
public class PayServiceImpl implements PayService {
    @Override
    public Result<PaymentCreateResponse> create(PaymentCreateRequest request) {
        return null;
    }

    @Override
    public Result<PayVerifyResponse> verify(PayVerifyRequest request) {
        return null;
    }

    @Override
    public Result<PayoutResponse> payout(PayoutRequest request) {
        return null;
    }

}
