package study.daydayup.wolf.framework.rpc.dubbo.generic;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author weixing
 * @since 2023/2/10 16:08
 */
public class GenericServiceFactory {

    public static GenericService createAsync(String appName, String registryGroup, String registryAddress, String consumerGroup, String service, String version) {
        return createService(appName, registryGroup, registryAddress, consumerGroup, service, version, true);
    }

    public static GenericService create(String appName, String registryGroup, String registryAddress, String consumerGroup, String service, String version) {
        return createService(appName, registryGroup, registryAddress, consumerGroup, service, version, false);
    }

    public static GenericService create(String appName, String registryGroup, String registryAddress, String consumerGroup, Class<?> interfaceClass, String version) {
        return createService(appName, registryGroup, registryAddress, consumerGroup, interfaceClass, version, false);
    }

    private static <T> GenericService createService(String appName, String registryGroup, String registryAddress,
                                                    String consumerGroup, T service, String version, boolean async) {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(appName);

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setId("DEFAULT");
        registryConfig.setAddress(registryAddress);
        registryConfig.setGroup(registryGroup);

        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setProtocol("dubbo");
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setVersion(version);
        referenceConfig.setAsync(async);
        referenceConfig.setCheck(false);
        referenceConfig.setGeneric(true);
        referenceConfig.setGroup(consumerGroup);
        //referenceConfig.setId(service);

        if (service instanceof String) {
            String svr = (String) service;
            referenceConfig.setInterface(svr);
        } else {
            Class<?> cls = (Class<?>) service;
            referenceConfig.setInterface(cls);
        }

        ReferenceConfigCache configCache = ReferenceConfigCache.getCache();
        GenericService gs =  configCache.get(referenceConfig);

        //GenericService gs = referenceConfig.get();

        return gs;
    }

    public static void destroy(Class<?> interfaceClass) {
        ReferenceConfigCache configCache = ReferenceConfigCache.getCache();
        configCache.destroy(interfaceClass);
    }
}
