package com.onedot.win.framework.middleware.mq.vendor.rocketmq.acl;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import com.onedot.win.common.util.lang.StringUtil;
import com.onedot.win.framework.middleware.mq.config.MQVendorConfig;

/**
 * com.onedot.win.framework.middleware.mq.adapter.rocketmq.acl
 *
 * @author Wingle
 * @since 2021/12/16 下午6:05
 **/
public class AclHookFactory {
    public static AclClientRPCHook create(MQVendorConfig config) {
        if (StringUtil.isBlank(config.getAppKey())) {
            return null;
        }

        return new AclClientRPCHook(
                new SessionCredentials(
                        config.getAppKey(),
                        config.getAppSecret()
                )
        );
    }
}