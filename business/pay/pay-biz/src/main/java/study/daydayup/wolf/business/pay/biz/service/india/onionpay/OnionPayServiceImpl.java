package study.daydayup.wolf.business.pay.biz.service.india.onionpay;

import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeResponse;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.service.india.onionpay
 *
 * @author Wingle
 * @since 2020/7/4 5:37 下午
 **/
public class OnionPayServiceImpl implements OnionPayService {
    @Resource
    private OnionPayCreator onionPayCreator;

    @Override
    public Result<PaymentCreateResponse> create(PaymentCreateRequest request) {
        PaymentCreateResponse response = onionPayCreator.create(request);
        return Result.ok(response);
    }

    @Override
    public Result<PayVerifyResponse> verify(PayVerifyRequest request) {
        return null;
    }

    @Override
    public Result<SubscribeResponse> subscribe(SubscribeRequest request) {
        return null;
    }

    @Override
    public Result<PayoutResponse> payout(PayoutRequest request) {
        return null;
    }
}
