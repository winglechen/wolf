package com.wolf.framework.middleware.mq.vendor.rocketmq.acl;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.framework.middleware.mq.config.MQVendorConfig;

/**
 * com.wolf.framework.middleware.mq.adapter.rocketmq.acl
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