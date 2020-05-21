package study.daydayup.wolf.middleware.notice.biz.api;

import study.daydayup.wolf.middleware.notice.api.config.SMSConfig;
import study.daydayup.wolf.middleware.notice.api.domain.exception.SMSConfigNotFoundException;
import study.daydayup.wolf.middleware.notice.api.service.SMSService;
import study.daydayup.wolf.middleware.notice.biz.sms.china.ChinaSMSService;
import study.daydayup.wolf.middleware.notice.biz.sms.india.IndiaSMSService;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.biz.api
 *
 * @author Wingle
 * @since 2020/3/20 8:52 下午
 **/
@RpcService(protocol = "dubbo")
public class SMSServiceImpl implements SMSService {
    @Resource
    private IndiaSMSService indiaSMSService;
    @Resource
    private ChinaSMSService chinaSMSService;
    @Resource
    private SMSConfig config;

    @Override
    public int toIndia(String mobile, String msg) {
        validConfig();
        if (config.isMockMode()) {
            return 1;
        }

        return indiaSMSService.send(mobile, msg);
    }

    @Override
    public int toChina(String mobile, String msg) {
        validConfig();
        if (config.isMockMode()) {
            return 1;
        }

        return chinaSMSService.send(mobile, msg);
    }

    private void validConfig() {
        if (null == config) {
            throw new SMSConfigNotFoundException();
        }
    }
}
