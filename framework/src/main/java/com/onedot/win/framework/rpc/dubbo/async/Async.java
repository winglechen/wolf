package com.onedot.win.framework.rpc.dubbo.async;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
import com.onedot.win.framework.rpc.Result;
import com.onedot.win.framework.rpc.dubbo.async.chain.AsyncChainBuilder;
import com.onedot.win.framework.rpc.dubbo.async.group.AsyncGroupBuilder;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

/**
 * com.onedot.win.framework.rpc.dubbo
 *
 * @author Wingle
 * @since 2021/10/21 下午10:54
 **/
public class Async {
    public static void call(GenericService genericService, String methodName, String[] parameterTypes, Object[] args, Callback<Result> callback) {
        // 使用Dubbo的异步调用方式
        CompletableFuture<Object> future = RpcContext.getContext().asyncCall(
            () -> genericService.$invokeAsync(methodName, parameterTypes, args)
        );
        // 注册回调函数
        future.whenComplete((result, throwable) -> {
            Result res = new JSONObject((Map<String, Object>) result).toJavaObject(Result.class);
            if (throwable != null) {
                callback.onError(throwable);
            } else {
                callback.onSuccess(res);
            }
        });
    }

    /**
     * async dubbo call
     * @param callable rpc
     * @param <T> Result<T>
     * @return CompletableFuture<T>
     */
    public static <T> CompletableFuture<T> call(Callable<T> callable) {
        return RpcContext.getContext().asyncCall(callable);
    }

    /**
     * async dubbo request group
     * @return AsyncGroupBuilder
     */
    public static AsyncGroupBuilder group() {
        return new AsyncGroupBuilder();
    }

    /**
     * async dubbo chained request
     * TODO add Context parameter
     * @return AsyncChainBuilder
     */
    public static AsyncChainBuilder chain() {
        return new AsyncChainBuilder();
    }
}
