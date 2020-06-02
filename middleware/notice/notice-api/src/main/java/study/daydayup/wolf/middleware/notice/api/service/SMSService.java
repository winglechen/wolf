package study.daydayup.wolf.middleware.notice.api.service;

import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;

/**
 * study.daydayup.wolf.middleware.notice.api.service
 *
 * @author Wingle
 * @since 2020/3/19 4:33 下午
 **/
public interface SMSService extends Service {
    int toIndia(String mobile, String msg);
    int toIndia(String mobile, String msg, SMSSendConfig config);
    int toChina(String mobile, String msg);
    int toChina(String mobile, String msg, SMSSendConfig config);
}
