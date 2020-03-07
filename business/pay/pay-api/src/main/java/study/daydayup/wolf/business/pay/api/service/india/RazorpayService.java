package study.daydayup.wolf.business.pay.api.service.india;

import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.pay.api.service.india
 *
 * @author Wingle
 * @since 2020/2/26 3:16 下午
 **/
public interface RazorpayService {
    Result<PaymentCreateResponse> create(PaymentCreateRequest request);
    Result<PayVerifyResponse> verify(PayVerifyRequest request);
    Result<Integer> subscribe(String eventId, String signature, String data);
}
