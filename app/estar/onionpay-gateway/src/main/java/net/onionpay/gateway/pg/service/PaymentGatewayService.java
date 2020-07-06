package net.onionpay.gateway.pg.service;

import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import net.onionpay.gateway.pg.dto.CheckoutRequest;
import net.onionpay.gateway.pg.dto.PaymentStatusDTO;
import net.onionpay.gateway.pg.util.PaymentConverter;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentChannelEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.PaymentExpiredException;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.PaymentNotFoundException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.business.pay.api.service.PaymentService;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.domain.Service;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * net.onionpay.gateway.pg.service
 *
 * @author Wingle
 * @since 2020/7/3 5:04 下午
 **/
@Component
public class PaymentGatewayService implements Service {
    private static final int DEFAULT_PAYMENT_CHANNEL = PaymentChannelEnum.DLOCAL.getCode();

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Reference(timeout = 5000)
    private PayService payService;
    @Reference
    private PaymentService paymentService;


    public Payment loadByToken(@NonNull String token) {
        PaymentCreateRequest createRequest = loadRequestByToken(token);
        return PaymentConverter.fromCreateRequest(createRequest);
    }

    public PaymentStatusDTO findStatus(@NonNull String token) {
        PaymentCreateRequest createRequest = loadRequestByToken(token);
        if (StringUtil.isBlank(createRequest.getPaymentNo())) {
            throw new PaymentNotFoundException();
        }

        Payment payment = paymentService.findStatus(createRequest.getPaymentNo(), createRequest.getPayeeId()).getData();

        return toStatus(payment);
    }

    private PaymentStatusDTO toStatus(Payment payment) {
        if (payment == null) {
            return null;
        }

        PaymentStatusDTO statusDTO = new PaymentStatusDTO();
        BeanUtils.copyProperties(payment, statusDTO);
        if (BeanUtil.equals(4, payment.getState())) {
            statusDTO.setStateCode("SUCCESS");
        } else {
            statusDTO.setStateCode("PAYING");
        }

        return statusDTO;
    }

    public PaymentCreateResponse checkout(@Validated CheckoutRequest checkoutRequest) {
        PaymentCreateRequest createRequest = loadRequestByToken(checkoutRequest.getToken());
        createRequest.setPaymentChannel(DEFAULT_PAYMENT_CHANNEL);
        createRequest.setPaymentMode(checkoutRequest.getPaymentMode());

        PaymentCreateResponse response = payService.create(createRequest).notNullData();

        createRequest.setPaymentMode(response.getPaymentNo());
        resetRequestByToken(checkoutRequest.getToken(), createRequest);

        return response;
    }


    private PaymentCreateRequest loadRequestByToken(@NonNull String token) {
        String data = stringRedisTemplate.opsForValue().get(token);
        if (StringUtil.isBlank(data)) {
            throw new PaymentExpiredException();
        }

        return JSON.parseObject(data, PaymentCreateRequest.class);
    }

    private void resetRequestByToken(@NonNull String token, @NonNull PaymentCreateRequest createRequest) {
        String value = JSON.toJSONString(createRequest);
        stringRedisTemplate.opsForValue().set(token, value, Duration.ofMinutes(15));
    }
}
