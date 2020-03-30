package study.daydayup.wolf.demo.my.starter.spring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.demo.my.starter
 *
 * @author Wingle
 * @since 2019/12/4 8:52 下午
 **/
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
//@ConditionalOnClass(HelloService.class)
//@ConditionalOnProperty(
//        prefix = "hello",
//        value = "enabled",
//        matchIfMissing = true
//)
@ConditionalOnWebApplication
@ComponentScan("study.daydayup.wolf.demo.my.starter")
public class HelloAutoConfiguration {
    @Resource
    private HelloProperties helloProperties;

    @Bean
//    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.DEFAULT)
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
    public HelloService helloService() {
        System.out.println("create HelloService bean");

        HelloService helloService = new HelloService();
        helloService.setMsg(helloProperties.getMsg());
        helloService.setShow(helloProperties.isShow());

        return helloService;
    }

    @Bean
    public HelloContainer helloContainer() {
        return new HelloContainer();
    }
}
