package net.onionpay.gateway.pg.service;

import com.alibaba.fastjson.JSON;
import lombok.NonNull;
import net.onionpay.gateway.pg.util.PaymentConverter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.exception.pay.PaymentExpiredException;
import study.daydayup.wolf.business.pay.api.dto.base.pay.PaymentCreateRequest;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.layer.domain.Service;

import javax.annotation.Resource;

/**
 * net.onionpay.gateway.pg.service
 *
 * @author Wingle
 * @since 2020/7/3 5:04 下午
 **/
@Component
public class PaymentGatewayService implements Service {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public Payment loadByToken(@NonNull String token) {
        String data = stringRedisTemplate.opsForValue().get(token);
        if (StringUtil.isBlank(data)) {
            throw new PaymentExpiredException();
        }

        PaymentCreateRequest createRequest = JSON.parseObject(data, PaymentCreateRequest.class);
        return PaymentConverter.fromCreateRequest(createRequest);
    }
}
