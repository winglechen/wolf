package study.daydayup.wolf.business.pay.biz.service.india.dokypay;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutRequest;
import study.daydayup.wolf.business.pay.api.dto.base.payout.PayoutResponse;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.pay.biz.api.india
 *
 * @author Wingle
 * @since 2020/4/26 6:42 下午
 **/
@Component
public class DokypayServiceImpl implements DokypayService {
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
