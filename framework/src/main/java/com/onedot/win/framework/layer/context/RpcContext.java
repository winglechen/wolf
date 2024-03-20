package com.onedot.win.framework.layer.context;

import org.springframework.stereotype.Component;
import com.onedot.win.common.lang.contract.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * com.onedot.win.framework.layer.context
 *
 * Deprecated, use com.onedot.win.framework.util.RpcContextUtil instead.
 *
 * @author Wingle
 * @since 2020/1/9 9:08 下午
 **/
@Deprecated
@Component
public class RpcContext implements Context {
    /**
     * 操作人
     */
    public static final String OPERATOR_ID = "operatorId";

    /**
     * 商户id
     */
    public static final String ORG_ID = "orgId";
    /**
     * 操作人 name
     */
    public static final String ACCOUNT_NAME="accountName";

    private static final String REQUEST_TIME_KEY = "requestDateTime";

    public LocalDateTime getRequestTime() {
        org.apache.dubbo.rpc.RpcContext context = org.apache.dubbo.rpc.RpcContext.getContext();
        Object requestObject = context.get(REQUEST_TIME_KEY);
        if (requestObject != null) {
            return (LocalDateTime) requestObject;
        }

        LocalDateTime requestTime = LocalDateTime.now();
        context.set(REQUEST_TIME_KEY, requestTime);

        return requestTime;
    }

    public Long getLongAttachment(String key) {
        Object attachment = org.apache.dubbo.rpc.RpcContext.getContext().getObjectAttachment(key);
        if (attachment instanceof Long) {
            return (Long) attachment;
        }
        return null;
    }

    public LocalDate getRequestDate() {
        return getRequestTime().toLocalDate();
    }

    public Object get(String key) {
        org.apache.dubbo.rpc.RpcContext context = org.apache.dubbo.rpc.RpcContext.getContext();
        return context.get(key);
    }

    public void set(String key, Object value) {
        org.apache.dubbo.rpc.RpcContext context = org.apache.dubbo.rpc.RpcContext.getContext();
        context.set(key, value);
    }

    public void remove(String key) {
        org.apache.dubbo.rpc.RpcContext context = org.apache.dubbo.rpc.RpcContext.getContext();
        context.remove(key);
    }

    public Object getAttachment(String key) {
        org.apache.dubbo.rpc.RpcContext context = org.apache.dubbo.rpc.RpcContext.getContext();
        return context.getAttachment(key);
    }

    public Object setAttachment(String key, Object value) {
        org.apache.dubbo.rpc.RpcContext context = org.apache.dubbo.rpc.RpcContext.getContext();
        return context.setAttachment(key, value);
    }
}
