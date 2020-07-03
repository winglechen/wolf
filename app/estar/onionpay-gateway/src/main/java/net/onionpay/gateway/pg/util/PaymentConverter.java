package net.onionpay.gateway.pg.util;

import org.springframework.beans.BeanUtils;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;

/**
 * net.onionpay.gateway.pg.service
 *
 * @author Wingle
 * @since 2020/7/3 5:02 下午
 **/
public class PaymentConverter {
    public static Payment fromCreateRequest(PaymentCreateRequest createRequest) {
        if (createRequest == null) {
            return null;
        }

        Payment payment = new Payment();
        BeanUtils.copyProperties(createRequest, payment);

        return payment;
    }
}
