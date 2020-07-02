package net.onionpay.gateway.controller.pg;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * net.onionpay.gateway.controller.pg
 *
 * @author Wingle
 * @since 2020/7/2 3:51 下午
 **/
@RestController
public class PaymentGatewayController {

    @GetMapping("/pg/payment/detailByToken/{token}")
    public Result<Payment> detailByToken() {
        return null;
    }

    @PostMapping("/pg/payment/checkout")
    public Result<PaymentCreateResponse> checkout() {
        return null;
    }

}
