package com.onedot.win.framework.util;

import com.onedot.win.common.util.time.DateUtil;

import java.time.LocalDateTime;

/**
 *
 */
public class RpcContextUtil {

    public static final String OPERATOR_ID = "operatorId";
    public static final String OPERATOR_NAME = "operatorName";
    private static final String REQUEST_TIME_KEY = "requestDateTime";

    public static LocalDateTime getRequestTime() {
        String requestTimeStr = getAttachment(REQUEST_TIME_KEY);
        if (requestTimeStr != null) {
            return DateUtil.asLocalDateTime(requestTimeStr);
        }

        LocalDateTime requestTime = LocalDateTime.now();
        setRequestTime(requestTime);

        return requestTime;
    }

    public static void setRequestTime(LocalDateTime requestTime) {
        setAttachment(REQUEST_TIME_KEY, DateUtil.asString(requestTime));
    }

    public static Long getOperatorId() {
        String lastEditor = getAttachment(OPERATOR_ID);
        if (null == lastEditor) {
            return 0L;
        }
        return Long.valueOf(lastEditor);
    }

    public static void setOperatorId(Long id) {
        setAttachment(OPERATOR_ID, id.toString());
    }

    public static String getOperatorName() {
        return getAttachment(OPERATOR_NAME);
    }

    public static void setOperatorName(String operatorName) {
        setAttachment(OPERATOR_NAME, operatorName);
    }

    public static String getAttachment(String key) {
        org.apache.dubbo.rpc.RpcContext context = org.apache.dubbo.rpc.RpcContext.getContext();
        return context.getAttachment(key);
    }

    public static void setAttachment(String key, String value) {
        org.apache.dubbo.rpc.RpcContext context = org.apache.dubbo.rpc.RpcContext.getContext();
        context.setAttachment(key, value);
    }
}
