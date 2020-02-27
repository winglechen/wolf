package study.daydayup.wolf.business.union.app.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.pay.api.dto.PayRequest;
import study.daydayup.wolf.business.pay.api.dto.PayResponse;
import study.daydayup.wolf.business.pay.api.service.india.RazorpayService;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.union.app.controller
 *
 * @author Wingle
 * @since 2020/2/27 5:44 下午
 **/
@RestController
public class UnionPayController {
    @Reference
    private RazorpayService razorpayService;


    @PostMapping("/pay/pay")
    public Result<PayResponse> pay(PayRequest request) {
        return null;
    }

    @PostMapping("/pay/razorpay/pay")
    public Result<PayResponse> razorPay(PayRequest request) {
        return null;
    }

    @PostMapping("/pay/razorpay/subscribe")
    public Result<String> razorSubscribe(@RequestBody String data) {
        return razorpayService.subscribe(data);
    }
}
