package study.daydayup.wolf.business.union.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PayVerifyResponse;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeRequest;
import study.daydayup.wolf.business.pay.api.dto.base.subscribe.SubscribeResponse;
import study.daydayup.wolf.business.pay.api.service.PayService;
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
    @Reference(timeout = 12000)
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

        Map<String, String> header = new HashMap<>(2);
        header.put("eventId", eventId);
        header.put("signature", signature);

        SubscribeRequest request = SubscribeRequest.builder()
                .paymentMethod(PaymentChannelEnum.RAZORPAY.getCode())
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
                .paymentMethod(PaymentChannelEnum.DOKYPAY.getCode())
                .data(data)
                .build();

        SubscribeResponse response = payService.subscribe(request).getData();
        if (!BeanUtil.equals(response.getCode(), 1)) {
            servletResponse.setStatus(500);
            return "fail";
        }

        return "success";

    }

    @PostMapping("/pay/cashfree/subscribe")
    public String cashfreeSubscribe(HttpServletResponse servletResponse, @RequestBody String data) {
        log.info("cashfree subscribe: {}", data);

        SubscribeRequest request = SubscribeRequest.builder()
                .paymentMethod(PaymentChannelEnum.CASHFREE.getCode())
                .data(data)
                .build();

        SubscribeResponse response = payService.subscribe(request).getData();
        if (!BeanUtil.equals(response.getCode(), 1)) {
            servletResponse.setStatus(500);
            return "fail";
        }

        return "success";
    }

    @PostMapping("/pay/dLocal/subscribe")
    public String dLocalSubscribe(HttpServletResponse servletResponse,
                                  @RequestHeader(value = "Authorization") String authorization,
                                  @RequestHeader(value = "X-Date") String xDate,
                                  @RequestBody String data) {

        Map<String, String> header = new HashMap<>(2);
        header.put("Authorization", authorization);
        header.put("X-Date", xDate);

        SubscribeRequest request = SubscribeRequest.builder()
                .paymentMethod(PaymentChannelEnum.DLOCAL.getCode())
                .data(data)
                .header(header)
                .build();

        log.info("dLocal subscribe: {}", request.getPayData());

        SubscribeResponse response = payService.subscribe(request).getData();
        if (!BeanUtil.equals(response.getCode(), 1)) {
            servletResponse.setStatus(500);
            return "fail";
        }

        return "success";
    }

    private void debugHeaders(HttpServletResponse servletResponse) {
        System.out.println("headers----------------------------");

        for (String name: servletResponse.getHeaderNames()) {
            System.out.println(name + " : " + servletResponse.getHeader(name));
        }

        System.out.println("headers*****************************");
    }



}
