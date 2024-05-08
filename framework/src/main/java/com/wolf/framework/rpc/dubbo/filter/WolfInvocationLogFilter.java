package com.wolf.framework.rpc.dubbo.filter;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wolf.common.lang.exception.BaseException;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;
import static org.apache.dubbo.rpc.Constants.ACCESS_LOG_KEY;

/**
 * @author weixing
 * @date 2022/5/23 15:30
 */
@Activate(group = {PROVIDER}, value = ACCESS_LOG_KEY)
public class WolfInvocationLogFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(WolfInvocationLogFilter.class);

    /**
     * Perform the validation of before invoking the actual method based on <b>validation</b> attribute value.
     * @param invoker    service
     * @param invocation invocation.
     * @return Method invocation result
     * @throws RpcException Throws RpcException if  validation failed or any other runtime exception occurred.
     */
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        logRequest(invocation);

        Result result = null;
        Throwable throwable = null;

        long startTime = System.currentTimeMillis();

        try {
            result = invoker.invoke(invocation);
        } catch (Throwable e) {
            throwable = e;
            throw e;
        } finally {
            long elapsedMills = System.currentTimeMillis() - startTime;
            logResponse(invocation, result, throwable, elapsedMills);
        }

        return result;
    }

    private void logRequest(Invocation invocation) {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("service", invocation.getInvoker().getInterface().getName());
        map.put("method", invocation.getMethodName());
        //map.put("paramTypes", invocation.getParameterTypes());
        map.put("params", invocation.getArguments());

        try {
            log.info("rpc request: " + JSON.toJSONString(map));
        } catch (Exception x) {
            log.error("log request error.", x);
        }
    }

    private void logResponse(Invocation invocation, Result result, Throwable e, long elapsedMills) {
        String service = invocation.getInvoker().getInterface().getName();
        String method = invocation.getMethodName();

        /*
        EnableLog enableLog =
        boolean logResponse = null != enableLog && enableLog.response();
        boolean logParamTypes = null != enableLog && enableLog.paramTypes();*/

        //boolean logResponse = service.contains("com.wolf.business.pay.");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("costTime", elapsedMills);
        map.put("success", e == null);
        map.put("code", getCode(result, e));
        map.put("service", service);
        map.put("method", method);
        /*if (logParamTypes) {
            map.put("paramTypes", invocation.getParameterTypes());
        }*/
        map.put("params", invocation.getArguments());
        map.put("response", "-");

        try {
            log.info("rpc response: " + JSON.toJSONString(map));
        } catch (Exception x) {
            log.error("log response error.", x);
        }
    }

    private long getCode(Result result, Throwable e) {
        if (e == null) {
            return 0;
        }

        if (e instanceof BaseException) {
            return ((BaseException) e).getCode();
        }

        return -1;
    }
}
