package study.daydayup.wolf.middleware.notice.biz.domain.service.sms;

import study.daydayup.wolf.middleware.notice.api.domain.entity.SMS;
import study.daydayup.wolf.framework.layer.domain.Service;

import java.util.Collection;

/**
 * study.daydayup.wolf.business.uc.biz.domain.service.sms
 *
 * @author Wingle
 * @since 2020/3/20 5:16 下午
 **/
public interface SMSSender extends Service {
    int send(String mobile, String msg);
    int bulkSend(Collection<SMS> smsList);
}
