package study.daydayup.wolf.business.pay.biz.domain.service;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.config.PayConfig;
import study.daydayup.wolf.business.pay.api.config.PaySupplier;
import study.daydayup.wolf.business.pay.api.domain.entity.PaymentLog;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
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

    @Resource
    protected PayConfig payConfig;

    @Resource
    private PaymentLogRepository logRepository;

    @Override
    public void initConfig(@NonNull String configKey) {
        config = payConfig.getSupplier().get(configKey);
    }

    @Override
    public void logResponse(int logType, int paymentMethod, String data) {
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
