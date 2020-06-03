package study.daydayup.wolf.middleware.notice.biz.api;

import study.daydayup.wolf.framework.rpc.Result;
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
    public Result<Integer> toIndia(String mobile, String msg) {
        return toIndia(mobile, msg, new SMSSendConfig());
    }

    @Override
    public Result<Integer> toIndia(String mobile, String msg, SMSSendConfig config) {
        validConfig();
        if (smsConfig.isMockMode()) {
            return Result.ok(1);
        }

        int status = indiaSMSSender.send(mobile, msg, config);
        return Result.ok(status);
    }

    @Override
    public Result<Integer> toChina(String mobile, String msg) {
        return toChina(mobile, msg, new SMSSendConfig());
    }

    @Override
    public Result<Integer> toChina(String mobile, String msg, SMSSendConfig config) {
        validConfig();
        if (smsConfig.isMockMode()) {
            return Result.ok(1);
        }

        int status = chinaSMSSender.send(mobile, msg, config);
        return Result.ok(status);
    }





    private void validConfig() {
        if (null == smsConfig) {
            throw new SMSConfigNotFoundException();
        }
    }
}
