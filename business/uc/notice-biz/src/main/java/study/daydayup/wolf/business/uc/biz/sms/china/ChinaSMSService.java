package study.daydayup.wolf.business.uc.biz.sms.china;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.notice.domain.entity.SMS;
import study.daydayup.wolf.business.uc.biz.domain.service.sms.Sender;

import java.util.Collection;

/**
 * study.daydayup.wolf.business.uc.biz.sms.china
 *
 * @author Wingle
 * @since 2020/3/20 8:57 下午
 **/
@Component
public class ChinaSMSService implements Sender {
    @Override
    public int send(String mobile, String msg) {
        return 0;
    }

    @Override
    public int bulkSend(Collection<SMS> smsList) {
        return 0;
    }
}
