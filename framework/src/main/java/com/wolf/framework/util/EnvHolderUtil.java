package com.wolf.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
/**
 * @author weixing
 * @date 2022/3/22 12:58
 */
@Component
public class EnvHolderUtil implements EnvironmentAware, DisposableBean {

    public static final String ENV_DEV = "dev";

    private static Environment environment;

    private static final Logger logger = LoggerFactory.getLogger(EnvHolderUtil.class);


    public static boolean isDev() {
        return isEnv(ENV_DEV);
    }

    public static boolean isEnv(String envCode) {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length == 0) {
            logger.error("EnvUtil: Environment active profile is empty!");
            return false;
        }
        return activeProfiles[0].equals(envCode);
    }

    public static boolean inEnv(String... envCode) {
        for (String code : envCode) {
            if (isEnv(code)) {
                return true;
            }
        }
        return false;
    }

    public static String getEnv() {
        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length == 0) {
            return "";
        }
        return activeProfiles[0];
    }

    public static String getProperty(String key) {
        return environment.getProperty(key);
    }

    @Override
    public void setEnvironment(@NonNull Environment env) {
        EnvHolderUtil.environment = env;
        logger.info("inject Environment to EnvUtil:" + env);
    }

    @Override
    public void destroy() throws Exception {
        logger.info("clear EnvUtil's Environment:" + EnvHolderUtil.environment);
        EnvHolderUtil.environment = null;
    }
}