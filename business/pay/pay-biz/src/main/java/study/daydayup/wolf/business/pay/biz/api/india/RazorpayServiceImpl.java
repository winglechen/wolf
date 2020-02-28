package study.daydayup.wolf.business.pay.biz.api.india;

import study.daydayup.wolf.business.pay.api.config.india.RazorConfig;
import study.daydayup.wolf.business.pay.api.dto.base.PayRequest;
import study.daydayup.wolf.business.pay.api.dto.base.PayResponse;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.service.india.RazorpayService;
import study.daydayup.wolf.business.pay.biz.service.india.razorpay.RazorCreator;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.api.china
 *
 * @author Wingle
 * @since 2020/2/27 7:37 下午
 **/
//@RpcService(protocol = "dubbo")
public class RazorpayServiceImpl implements RazorpayService {
    @Resource
    private RazorConfig config;
    @Resource
    private RazorCreator creator;

    @Override
    public Result<PaymentCreateResponse> create(PaymentCreateRequest request) {
        PaymentCreateResponse response = creator.create(request);
        return Result.ok(response);
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
