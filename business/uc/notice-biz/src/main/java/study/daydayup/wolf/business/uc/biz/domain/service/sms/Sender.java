package study.daydayup.wolf.business.uc.biz.domain.service.sms;

import study.daydayup.wolf.business.uc.api.notice.domain.entity.SMS;
import study.daydayup.wolf.framework.layer.domain.Service;

import java.util.Collection;

/**
 * study.daydayup.wolf.business.uc.biz.domain.service.sms
 *
 * @author Wingle
 * @since 2020/3/20 5:16 下午
 **/
public interface Sender extends Service {
    int send(String mobile, String msg);
    int bulkSend(Collection<SMS> smsList);
}
