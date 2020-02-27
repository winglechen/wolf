package study.daydayup.wolf.business.pay.api.service.india;

import study.daydayup.wolf.business.pay.api.dto.PayRequest;
import study.daydayup.wolf.business.pay.api.dto.PayResponse;
import study.daydayup.wolf.business.pay.api.dto.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.PaymentCreateResponse;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.pay.api.service.india
 *
 * @author Wingle
 * @since 2020/2/26 3:16 下午
 **/
public interface RazorpayService {
    Result<PaymentCreateResponse> create(PaymentCreateRequest request);
    Result<PayResponse> pay(PayRequest request);
    Result<String> subscribe(String data);
}
