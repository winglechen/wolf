package study.daydayup.wolf.business.uc.biz.sms.india;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.notice.domain.entity.SMS;
import study.daydayup.wolf.business.uc.biz.domain.service.sms.Sender;
import study.daydayup.wolf.business.uc.biz.sms.india.skyline.SkylineSender;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * study.daydayup.wolf.business.uc.biz.sms.india
 *
 * @author Wingle
 * @since 2020/3/20 8:54 下午
 **/
@Component
public class IndiaSMSService implements Sender {
    @Resource
    private SkylineSender skylineSender;

    @Override
    public int send(String mobile, String msg) {
        if (null == mobile || null == msg) {
            return 0;
        }

        return skylineSender.send(mobile, msg);
    }

    @Override
    public int bulkSend(Collection<SMS> smsList) {
        return 0;
    }
}
