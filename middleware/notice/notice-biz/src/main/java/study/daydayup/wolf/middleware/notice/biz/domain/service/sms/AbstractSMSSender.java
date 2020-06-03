package study.daydayup.wolf.middleware.notice.biz.domain.service.sms;

import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;
import study.daydayup.wolf.middleware.notice.api.domain.entity.SMS;
import study.daydayup.wolf.middleware.notice.api.domain.exception.SupplierNotSupportException;

import java.util.Collection;

/**
 * study.daydayup.wolf.business.uc.biz.domain.service.sms
 *
 * @author Wingle
 * @since 2020/3/20 5:18 下午
 **/
public abstract class AbstractSMSSender implements SMSSender {
    @Override
    public int send(String mobile, String msg, SMSSendConfig config) {
        throw new SupplierNotSupportException();
    }

    @Override
    public int bulkSend(Collection<SMS> smsList, SMSSendConfig config) {
        throw new SupplierNotSupportException();
    }
}
