package study.daydayup.wolf.business.union.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentMethodEnum;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeResponse;
import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.business.pay.api.service.india.RazorpayService;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.framework.rpc.Result;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * study.daydayup.wolf.business.union.app.controller
 *
 * @author Wingle
 * @since 2020/2/27 5:44 下午
 **/
@RestController
@Slf4j
public class UnionPayController {
    @Reference(timeout = 10000)
    private PayService payService;

    @PostMapping("/pay/verify")
    public Result<PayVerifyResponse> verify(PayVerifyRequest request) {
        return null;
    }

    @PostMapping("/pay/razorpay/verify")
    public Result<PayVerifyResponse> razorpayVerify(PayVerifyRequest request) {
        return null;
    }

    @PostMapping("/pay/razorpay/subscribe")
    public Result<String> razorpaySubscribe(HttpServletResponse servletResponse, @RequestHeader(value = "X-Razorpay-Event-Id", required = false) String eventId, @RequestHeader("X-Razorpay-Signature") String signature, @RequestBody String data) {
        log.info("razorpay subscribe:{}, {}, {}", eventId, signature, data);

        Map<String, Object> header = new HashMap<>(2);
        header.put("eventId", eventId);
        header.put("signature", signature);

        SubscribeRequest request = SubscribeRequest.builder()
                .paymentMethod(PaymentMethodEnum.RAZORPAY.getCode())
                .header(header)
                .data(data)
                .build();

        SubscribeResponse response = payService.subscribe(request).getData();
        if (!BeanUtil.equals(response.getCode(), 1)) {
            servletResponse.setStatus(500);
            return Result.fail(500, "inner error", "fail");
        }

        return Result.ok("ok");
    }

    @PostMapping("/pay/dokypay/subscribe")
    public String dokypaySubscribe(HttpServletResponse servletResponse, @RequestBody String data) {
        log.info("dokypay subscribe: {}", data);

        SubscribeRequest request = SubscribeRequest.builder()
                .paymentMethod(PaymentMethodEnum.DOKYPAY.getCode())
                .data(data)
                .build();

        SubscribeResponse response = payService.subscribe(request).getData();
        if (!BeanUtil.equals(response.getCode(), 1)) {
            servletResponse.setStatus(500);
            return "fail";
        }

        return "success";

    }
}
