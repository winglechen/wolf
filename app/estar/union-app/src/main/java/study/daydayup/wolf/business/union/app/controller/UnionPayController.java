package study.daydayup.wolf.business.union.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.service.india.RazorpayService;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.union.app.controller
 *
 * @author Wingle
 * @since 2020/2/27 5:44 下午
 **/
@RestController
@Slf4j
public class UnionPayController {
    @Reference
    private RazorpayService razorpayService;


    @PostMapping("/pay/verify")
    public Result<PayVerifyResponse> verify(PayVerifyRequest request) {
        return null;
    }

    @PostMapping("/pay/razorpay/verify")
    public Result<PayVerifyResponse> razorpayVerify(PayVerifyRequest request) {
        return null;
    }

    @PostMapping("/pay/razorpay/subscribe")
    public Result<String> razorpaySubscribe(@RequestBody String data) {
        log.info("razorpay:{}", data);
        //TODO check response and return code != 200 when response fail
        return razorpayService.subscribe(data);
    }
}
