package study.daydayup.wolf.business.uc.biz.sms.india.skyline;

import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.biz.domain.service.sms.AbstractSender;
import study.daydayup.wolf.business.uc.biz.domain.service.sms.Sender;

/**
 * study.daydayup.wolf.business.uc.biz.sms.india.skyline
 *
 * @author Wingle
 * @since 2020/3/19 7:41 下午
 **/
@Component
public class SkylineSender extends AbstractSender implements Sender {
    @Override
    public int send(String mobile, String msg) {
        return 0;
    }
}
