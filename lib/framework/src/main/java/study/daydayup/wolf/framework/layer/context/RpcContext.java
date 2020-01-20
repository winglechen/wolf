package study.daydayup.wolf.framework.layer.context;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * study.daydayup.wolf.framework.layer.context
 *
 * @author Wingle
 * @since 2020/1/9 9:08 下午
 **/
@Component
public class RpcContext implements Context {
    private static final String REQUEST_TIME_KEY = "requestDateTime";
    private LocalDateTime requestTime;

    public LocalDateTime getRequestTime() {
        org.apache.dubbo.rpc.RpcContext context = org.apache.dubbo.rpc.RpcContext.getContext();
        Object requestObject = context.get(REQUEST_TIME_KEY);
        if (requestObject != null) {
            return (LocalDateTime)requestObject;
        }

        LocalDateTime requestTime = LocalDateTime.now();
        context.set(REQUEST_TIME_KEY, requestTime);

        return requestTime;
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
}
