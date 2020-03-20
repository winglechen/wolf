package study.daydayup.wolf.business.uc.biz.sms.india.skyline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.api.notice.config.SMSConfig;
import study.daydayup.wolf.business.uc.biz.domain.service.sms.AbstractSender;
import study.daydayup.wolf.business.uc.biz.domain.service.sms.Sender;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.biz.sms.india.skyline
 *
 * @author Wingle
 * @since 2020/3/19 7:41 下午
 **/
@Component
@Slf4j
public class SkylineSender extends AbstractSender implements Sender {
    @Resource
    private SMSConfig config;

    @Override
    public int send(String mobile, String msg) {
        validConfig();
        prepareRequest();
        sendSms();
        parseResponse();

        return 0;
    }

    private void validConfig() {

    }

    private void prepareRequest() {

    }

    private void sendSms() {

    }

    private void parseResponse() {

    }
}
