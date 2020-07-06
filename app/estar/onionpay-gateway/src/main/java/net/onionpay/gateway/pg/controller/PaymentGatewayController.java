package net.onionpay.gateway.pg.controller;

import net.onionpay.gateway.pg.dto.PaymentStatusDTO;
import net.onionpay.gateway.pg.service.PaymentGatewayService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;

import net.onionpay.gateway.pg.dto.CheckoutRequest;

import javax.annotation.Resource;

/**
 * net.onionpay.gateway.pg
 *
 * @author Wingle
 * @since 2020/7/2 3:51 下午
 **/
@RestController
public class PaymentGatewayController implements Controller {
    @Resource
    private PaymentGatewayService paymentGatewayService;

    @GetMapping("/pg/payment/detailByToken/{token}")
    public Result<Payment> detailByToken(@PathVariable("token") String token) {
        System.out.println("detailByToken");
        return null;
//        if (StringUtil.isBlank(token)) {
//            throw new IllegalArgumentException("token can't be blank");
//        }
//
//        Payment payment = paymentGatewayService.loadByToken(token);
//        return Result.ok(payment);
    }

    @PostMapping("/pg/payment/checkout")
    public Result<PaymentCreateResponse> checkout(@Validated @RequestBody CheckoutRequest request) {
        PaymentCreateResponse response = paymentGatewayService.checkout(request);
        return Result.ok(response);
    }

    @GetMapping("/pg/payment/status/{token}")
    public Result<PaymentStatusDTO> status(@PathVariable("token") String token) {
        if (StringUtil.isBlank(token)) {
            throw new IllegalArgumentException("token can't be blank");
        }

        PaymentStatusDTO status = paymentGatewayService.findStatus(token);
        return Result.ok(status);
    }

    @GetMapping("/pg/payment/cancel/{token}")
    public Result<PaymentStatusDTO> cancel(@PathVariable("token") String token) {
        if (StringUtil.isBlank(token)) {
            throw new IllegalArgumentException("token can't be blank");
        }

        PaymentStatusDTO status = paymentGatewayService.cancel(token);
        return Result.ok(status);
    }

}
