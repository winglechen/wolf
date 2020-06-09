package study.daydayup.wolf.middleware.notice.biz.domain.service.sms;

import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import study.daydayup.wolf.business.uc.setting.agent.CompanySettingAgent;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.util.lang.JsonUtil;
import study.daydayup.wolf.middleware.notice.api.config.SMSConfig;
import study.daydayup.wolf.middleware.notice.api.config.SMSSendConfig;
import study.daydayup.wolf.middleware.notice.api.config.sms.SMSSupplier;
import study.daydayup.wolf.middleware.notice.api.domain.entity.SMS;
import study.daydayup.wolf.middleware.notice.api.domain.exception.InvalidSMSConfigException;
import study.daydayup.wolf.middleware.notice.api.domain.exception.SupplierNotSupportException;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * study.daydayup.wolf.business.uc.biz.domain.service.sms
 *
 * @author Wingle
 * @since 2020/3/20 5:18 下午
 **/
public abstract class AbstractSMSSender implements SMSSender {
    protected SMSSendConfig smsSendConfig;
    @Resource
    protected CompanySettingAgent companySettingAgent;

    @Resource
    protected SMSConfig smsConfig;
    protected SMSSupplier supplierConfig;

    protected String mobile;
    protected String msg;

    @Override
    public int send(String mobile, String msg, SMSSendConfig config) {
        throw new SupplierNotSupportException();
    }

    @Override
    public int bulkSend(Collection<SMS> smsList, SMSSendConfig config) {
        throw new SupplierNotSupportException();
    }


    protected void validConfig(@NonNull String configKey) {
        if (null == smsConfig.getSupplier()) {
            throw new InvalidSMSConfigException();
        }
        if (null == smsConfig.getSupplier().get(configKey)) {
            throw new InvalidSMSConfigException();
        }

        supplierConfig = smsConfig.getSupplier().get(configKey);
        mergeOrgSetting(configKey);
    }

    protected void mergeOrgSetting(@NonNull String configKey) {
        if (null == smsSendConfig || null == smsSendConfig.getOrgId()) {
            return;
        }

        companySettingAgent.init(smsSendConfig.getOrgId(), false);
        companySettingAgent.namespace(SMSConfig.SMS_NAMESPACE);
        ObjectMap orgSetting = companySettingAgent.getAll();
        if (null == orgSetting) {
            throw new InvalidSMSConfigException("sms config not found");
        }

        JSONObject supplier = JsonUtil.getJSONObject(orgSetting, "supplier", configKey);
        if (supplier == null) {
            throw new InvalidSMSConfigException("sms config not found");
        }

        supplierConfig.setSignature(supplier.getString("signature"));
        supplierConfig.setSenderNum(supplier.getString("senderNum"));
        supplierConfig.setBrand(supplier.getString("brand"));
        supplierConfig.setAppId(supplier.getString("appId"));
        supplierConfig.setAppSecret(supplier.getString("appSecret"));
        supplierConfig.setSendUrl(supplier.getString("sendUrl"));
    }

    protected void parseMsg() {
        msg = msg.replace(SMSConfig.BRAND_PLACEHOLDER, supplierConfig.getBrand());
    }
}
