package study.daydayup.wolf.business.pay.biz.domain.service;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.pay.api.domain.entity.PaymentLog;
import study.daydayup.wolf.business.pay.biz.domain.repository.PaymentLogRepository;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.pay.biz.domain.service
 *
 * @author Wingle
 * @since 2020/4/29 7:23 下午
 **/
@Component
public abstract class AbstractPaymentSubscriber implements PaymentSubscriber {
    @Resource
    private PaymentLogRepository logRepository;

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
