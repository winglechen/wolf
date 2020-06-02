package study.daydayup.wolf.middleware.notice.api.service;

import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;

/**
 * study.daydayup.wolf.middleware.notice.api.service
 *
 * @author Wingle
 * @since 2020/3/19 4:33 下午
 **/
public interface SMSService extends Service {
    Result<Integer> toIndia(String mobile, String msg);
    Result<Integer> toIndia(String mobile, String msg, SMSSendConfig config);
    Result<Integer> toChina(String mobile, String msg);
    Result<Integer> toChina(String mobile, String msg, SMSSendConfig config);
}
