package study.daydayup.wolf.middleware.notice.biz.sms.india;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;
import study.daydayup.wolf.middleware.notice.api.domain.entity.SMS;
import study.daydayup.wolf.middleware.notice.biz.domain.service.sms.SMSSender;
import study.daydayup.wolf.middleware.notice.biz.sms.india.nxcloud.NxcloudSMSSender;
import study.daydayup.wolf.middleware.notice.biz.sms.india.skyline.SkylineSender;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * study.daydayup.wolf.business.uc.biz.sms.india
 *
 * @author Wingle
 * @since 2020/3/20 8:54 下午
 **/
@Component
public class IndiaSMSSender implements SMSSender {
    @Resource
    private SkylineSender skylineSender;
    @Resource
    private NxcloudSMSSender nxcloudSMSSender;

    @Override
    public int send(String mobile, String msg, SMSSendConfig config) {
        if (null == mobile || null == msg) {
            return 0;
        }

//        return nxcloudSMSSender.send(mobile, msg, config);
        return skylineSender.send(mobile, msg, config);
    }

    @Override
    public int bulkSend(Collection<SMS> smsList, SMSSendConfig config) {
        return 0;
    }
}
