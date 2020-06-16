package study.daydayup.wolf.business.pay.biz.domain.service;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.api.domain.entity.Payment;
import study.daydayup.wolf.business.pay.api.domain.entity.PaymentLog;
import study.daydayup.wolf.business.pay.api.domain.enums.PaymentLogTypeEnum;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentRepository;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.domain.service
 *
 * @author Wingle
 * @since 2020/6/10 8:35 下午
 **/
@Component
public abstract class AbstractPaymentDomainService implements PaymentDomainService {
    protected PaySupplier config;
    protected Payment payment;
    @Resource
    protected PayConfig payConfig;

    @Resource
    protected PaymentRepository paymentRepository;

    @Resource
    private PaymentLogRepository logRepository;


    @Override
    public void initConfig(@NonNull String configKey) {
        initConfig(configKey, null);
    }

    public void initConfig(@NonNull String configKey, Long payeeId) {
        config = payConfig.getSupplier().get(configKey);
    }



    @Override
    public void logCreateResponse(String data) {
        PaymentLog log = PaymentLog.builder()
                .paymentNo(payment.getPaymentNo())
                .payeeId(payment.getPayeeId())
                .payerId(payment.getPayerId())
                .tradeNo(payment.getTradeNo())
                .paymentMethod(payment.getPaymentMethod())
                .logType(PaymentLogTypeEnum.PAY_REQUEST.getCode())
                .data(data)
                .build();

        logRepository.add(log);
    }

    @Override
    public void logSubscribeResponse(int logType, int paymentMethod, String data) {
        if (StringUtil.isBlank(data)) {
            return;
        }

        PaymentLog log = PaymentLog.builder()
                .logType(logType)
                .paymentMethod(paymentMethod)
                .data(data)
                .build();
        logRepository.add(log);
    }
}
