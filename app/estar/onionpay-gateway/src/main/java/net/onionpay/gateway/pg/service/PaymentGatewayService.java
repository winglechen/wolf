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
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentStateEnum;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.PaymentExpiredException;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.PaymentNotFoundException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateResponse;
import study.daydayup.wolf.business.pay.api.service.OnionPayConfigService;
import study.daydayup.wolf.business.pay.api.service.PayService;
import study.daydayup.wolf.business.pay.api.service.PaymentService;
import study.daydayup.wolf.common.util.lang.BeanUtil;
import study.daydayup.wolf.common.util.lang.EnumUtil;
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
    @Reference
    private OnionPayConfigService onionPayConfigService;


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

    public PaymentStatusDTO cancel(@NonNull String token) {
        PaymentCreateRequest createRequest = loadRequestByToken(token);
        Payment payment = PaymentConverter.fromCreateRequest(createRequest);

        return toStatus(payment);
    }

    public PaymentCreateResponse checkout(@Validated CheckoutRequest checkoutRequest) {
        PaymentCreateRequest createRequest = loadRequestByToken(checkoutRequest.getToken());
        createRequest.setPaymentChannel(DEFAULT_PAYMENT_CHANNEL);
        createRequest.setPaymentMode(checkoutRequest.getPaymentMode());
        createRequest.setReturnUrl(getOnionPayReturnUrl(checkoutRequest.getToken()));

        PaymentCreateResponse response = payService.create(createRequest).notNullData();

        createRequest.setPaymentMode(response.getPaymentNo());
        resetRequestByToken(checkoutRequest.getToken(), createRequest);
        response.setReturnUrl(getReturnUrl(createRequest.getPaymentChannel(), PaymentStateEnum.PAYING.getCode()));

        return response;
    }

    private String getOnionPayReturnUrl(String token) {
        String returnUrl = onionPayConfigService.findReturnUrl().notNullData();
        return returnUrl.replace("{token}",  token);
    }

    private String getReturnUrl(int paymentChannel, int state) {
        String returnUrl = onionPayConfigService.findReturnUrl(paymentChannel).notNullData();
        String params = getReturnUrlParams(paymentChannel, state);

        return StringUtil.join(returnUrl, params);
    }

    private String getReturnUrlParams(int paymentChannel, int state) {
        PaymentChannelEnum channelEnum = EnumUtil.codeOf(paymentChannel, PaymentChannelEnum.class);
        switch (channelEnum) {
            case DLOCAL:
                return getDLocalParams(state);
            case DOKYPAY:
                return getDokypayParams(state);
            case CASHFREE:
                return getCashfreeParams(state);
        }
        return null;
    }

    private String getDLocalParams(int state) {
        String stateCode = getStateCode(state);

        return StringUtil.join("?status=", stateCode);
    }


    private String getCashfreeParams(int state) {
        String stateCode = getStateCode(state);

        return StringUtil.join("?txStatus=", stateCode);
    }

    private String getDokypayParams(int state) {
        String stateCode = getDokypayStateCode(state);

        return StringUtil.join("?transStatus=", stateCode);
    }

    private PaymentStatusDTO toStatus(Payment payment) {
        if (payment == null) {
            return null;
        }

        PaymentStatusDTO statusDTO = new PaymentStatusDTO();
        BeanUtils.copyProperties(payment, statusDTO);

        statusDTO.setPaymentChannel(payment.getPaymentMethod());
        statusDTO.setStateCode(getStateCode(payment.getState()));
        statusDTO.setReturnUrl(getReturnUrl(payment.getPaymentMethod(), payment.getState()));

        return statusDTO;
    }

    private String getStateCode(Integer state) {
        if (BeanUtil.equals(4, state)) {
            return "SUCCESS";
        } else if(BeanUtil.equals(101, state)) {
            return "CANCELLED";
        } else {
            return "PENDING";
        }
    }

    private String getDokypayStateCode(Integer state) {
        if (BeanUtil.equals(4, state)) {
            return "success";
        } else if(BeanUtil.equals(101, state)) {
            return "cancel";
        } else {
            return "pending";
        }
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
