package study.daydayup.wolf.business.pay.biz.api.china;

import study.daydayup.wolf.business.pay.api.dto.base.PayRequest;
import study.daydayup.wolf.business.pay.api.dto.base.PayResponse;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.service.india.RazorpayService;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.pay.biz.api.china
 *
 * @author Wingle
 * @since 2020/2/27 7:37 下午
 **/
public class RazorpayServiceImpl implements RazorpayService {
    @Override
    public Result<PaymentCreateResponse> create(PaymentCreateRequest request) {
        return null;
    }

    @Override
    public Result<PayResponse> pay(PayRequest request) {
        return null;
    }

    @Override
    public Result<String> subscribe(String data) {
        return null;
    }
}
