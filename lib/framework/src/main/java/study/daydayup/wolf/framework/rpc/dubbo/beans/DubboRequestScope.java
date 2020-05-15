package study.daydayup.wolf.framework.rpc.dubbo.beans;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * study.daydayup.wolf.framework.rpc.dubbo
 *
 * @author Wingle
 * @since 2020/5/15 11:35 上午
 **/
public class DubboRequestScope implements Scope {
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        return null;
    }

    @Override
    public Object remove(String name) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
