package study.daydayup.wolf.business.pay.biz.domain.service;

import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.pay.biz.domain.service
 *
 * @author Wingle
 * @since 2020/3/1 12:45 上午
 **/
public interface PaymentDomainService extends Service {
    void initConfig(String configKey);
    void initConfig(String configKey, Long payeeId);
    void logResponse(int logType, int paymentMethod, String data);
}
