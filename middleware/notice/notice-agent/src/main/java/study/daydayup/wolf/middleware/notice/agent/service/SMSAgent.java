package study.daydayup.wolf.middleware.notice.agent.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.setting.api.dto.SettingDTO;
import study.daydayup.wolf.business.uc.setting.api.entity.CompanySetting;
import study.daydayup.wolf.business.uc.setting.api.service.CompanySettingService;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.middleware.notice.api.config.SMSConfig;
import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;
import study.daydayup.wolf.middleware.notice.api.domain.exception.InvalidSMSConfigException;
import study.daydayup.wolf.middleware.notice.api.service.SMSService;
import study.daydayup.wolf.framework.layer.api.Agent;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.middleware.notice.agent.notice
 *
 * @author Wingle
 * @since 2020/3/19 4:36 下午
 **/
@Component
public class SMSAgent implements Agent {

    @Reference
    private SMSService smsService;
    @Reference
    private CompanySettingService companySettingService;

    @Resource
    private SMSConfig smsConfig;

    public String getBrand(Long orgId) {
        if (orgId == null) {
            return smsConfig.getBrand();
        }

        SettingDTO query = SettingDTO.builder()
                .orgId(orgId)
                .namespace(smsConfig.SMS_NAMESPACE)
                .build();
        CompanySetting setting = companySettingService.findByNamespace(query).notNullData();
        if (StringUtil.isBlank(setting.getData())) {
            throw new InvalidSMSConfigException("sms config not found");
        }

        JSONObject json = JSON.parseObject(setting.getData());
        String brand = json.getString("brand");
        if (StringUtil.isBlank(brand)) {
            throw new InvalidSMSConfigException("sms brand not found");
        }

        return brand;
    }

    public int toIndia(String mobile, String msg) {
        return toIndia(mobile, msg, null);
    }

    public int toIndia(String mobile, String msg, SMSSendConfig config) {
        if (null == mobile || null == msg) {
            return 0;
        }

        return smsService.toIndia(mobile, msg, config).notNullData();
    }

    public int toChina(String mobile, String msg) {
        return toChina(mobile, msg, null);
    }

    public int toChina(String mobile, String msg, SMSSendConfig config) {
        if (null == mobile || null == msg) {
            return 0;
        }

        return smsService.toChina(mobile, msg, config).notNullData();
    }
}
