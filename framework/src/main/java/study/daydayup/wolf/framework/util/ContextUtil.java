package study.daydayup.wolf.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import study.daydayup.wolf.framework.exception.WolfException;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * study.daydayup.wolf.framework.layer.context
 *
 * @author Wingle
 * @since 2020/2/18 10:58 下午
 **/
@Component
public class ContextUtil implements ApplicationContextAware, DisposableBean {

    private static boolean isInit = false;

    private static ApplicationContext applicationContext;

    private static final Logger logger = LoggerFactory.getLogger(ContextUtil.class);

    public static void init(ApplicationContext context) {
        if (ContextUtil.applicationContext != null) {
            logger.warn("ContextUtil's ApplicationContext has been reset, origin ApplicationContext is:"
                    + ContextUtil.applicationContext
                    + " new ApplicationContext is:"
                    + context);
        }

        applicationContext = context;
        isInit = true;
    }

    public static ApplicationContext getContext() {
        assertContextInjected();
        return applicationContext;
    }

    public static <T> T getBean(Class<T> c) {
        if (!isInit) {
            throw new WolfException("BeanUtil does not init");
        }

        return applicationContext.getBean(c);
    }

    public static <T> T getBean(String className, Class<T> t) {
        if (!isInit) {
            throw new WolfException("BeanUtil does not init");
        }

        return applicationContext.getBean(className, t);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> aClass) {
        return applicationContext.getBeansOfType(aClass);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> aClass) {
        return applicationContext.getBeansWithAnnotation(aClass);
    };

    public static Object getBean(String name) {
        if (!isInit) {
            throw new WolfException("BeanUtil does not init");
        }
        return applicationContext.getBean(name);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        logger.info("inject ApplicationContext to ContextUtil:" + applicationContext);
        ContextUtil.init(applicationContext);
    }

    @Override
    public void destroy() throws Exception {
        ContextUtil.clear();
    }

    public static void clear() {
        if (isInit) {
            logger.info("clear ContextUtil's applicationContext:" + applicationContext);
        }
        applicationContext = null;
        isInit = false;
    }

    private static void assertContextInjected() {
        Assert.state(applicationContext != null, "applicationContext property not injected.");
    }
}
