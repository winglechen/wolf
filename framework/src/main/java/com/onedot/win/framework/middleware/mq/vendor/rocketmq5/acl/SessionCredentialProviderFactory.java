package com.onedot.win.framework.middleware.mq.vendor.rocketmq5.acl;

import org.apache.rocketmq.client.apis.SessionCredentialsProvider;
import org.apache.rocketmq.client.apis.StaticSessionCredentialsProvider;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.middleware.mq.config.MQVendorConfig;

/**
 * @author weixing
 * @since 2023/6/9 16:17
 */
public class SessionCredentialProviderFactory {
    public static SessionCredentialsProvider create(MQVendorConfig config) {
        if (StringUtil.isBlank(config.getAppKey())) {
            return null;
        }

        return new StaticSessionCredentialsProvider(
            config.getAppKey(),
            config.getAppSecret()
        );
    }
}
