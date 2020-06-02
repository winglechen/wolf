package study.daydayup.wolf.middleware.notice.biz.api;

import study.daydayup.wolf.middleware.notice.api.config.SMSConfig;
import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;
import study.daydayup.wolf.middleware.notice.api.domain.exception.SMSConfigNotFoundException;
import study.daydayup.wolf.middleware.notice.api.service.SMSService;
import study.daydayup.wolf.middleware.notice.biz.sms.china.ChinaSMSSender;
import study.daydayup.wolf.middleware.notice.biz.sms.india.IndiaSMSSender;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.biz.api
 *
 * @author Wingle
 * @since 2020/3/20 8:52 下午
 **/
@RpcService
public class SMSServiceImpl implements SMSService {
    @Resource
    private IndiaSMSSender indiaSMSSender;
    @Resource
    private ChinaSMSSender chinaSMSSender;
    @Resource
    private SMSConfig smsConfig;

    @Override
    public int toIndia(String mobile, String msg) {
        return toIndia(mobile, msg, new SMSSendConfig());
    }

    @Override
    public int toIndia(String mobile, String msg, SMSSendConfig config) {
        validConfig();
        if (smsConfig.isMockMode()) {
            return 1;
        }

        return indiaSMSSender.send(mobile, msg);
    }

    @Override
    public int toChina(String mobile, String msg) {
        return toChina(mobile, msg, new SMSSendConfig());
    }

    @Override
    public int toChina(String mobile, String msg, SMSSendConfig config) {
        validConfig();
        if (smsConfig.isMockMode()) {
            return 1;
        }

        return chinaSMSSender.send(mobile, msg);
    }





    private void validConfig() {
        if (null == smsConfig) {
            throw new SMSConfigNotFoundException();
        }
    }
}
