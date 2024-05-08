package com.wolf.framework.rpc.dubbo.async;

/**
 * @author weixing
 * @since 2023/3/20 19:22
 */
public interface Callback<T> {
    void onSuccess(T result);
    void onError(Throwable t);
}