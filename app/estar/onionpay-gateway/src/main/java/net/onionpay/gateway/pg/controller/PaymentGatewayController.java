package net.onionpay.gateway.pg.controller;

import net.onionpay.gateway.pg.dto.PaymentStatusDTO;
import net.onionpay.gateway.pg.service.PaymentGatewayService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.common.util.lang.StringUtil;
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
public class PaymentGatewayController {
    @Resource
    private PaymentGatewayService paymentGatewayService;

    @GetMapping("/pg/payment/detailByToken/{token}")
    public Result<Payment> detailByToken(@PathVariable("token") String token) {
        if (StringUtil.isBlank(token)) {
            throw new IllegalArgumentException("token can't be blank");
        }

        Payment payment = paymentGatewayService.loadByToken(token);
        return Result.ok(payment);
    }

    @PostMapping("/pg/payment/checkout")
    public Result<PaymentCreateResponse> checkout(@Validated @RequestBody CheckoutRequest request) {
        PaymentCreateResponse response = paymentGatewayService.checkout(request);
        return Result.ok(response);
    }

    @GetMapping("/pg/payment/status/{paymentNo}/{payeeId}/{token}")
    public Result<PaymentStatusDTO> status(@PathVariable("paymentNo") String paymentNo, @PathVariable("payeeId") Long payeeId, @PathVariable("token") String token) {
        if (StringUtil.isBlank(token)) {
            throw new IllegalArgumentException("token can't be blank");
        }

        PaymentStatusDTO status = paymentGatewayService.findStatus(paymentNo, payeeId, token);
        return Result.ok(status);
    }

}
