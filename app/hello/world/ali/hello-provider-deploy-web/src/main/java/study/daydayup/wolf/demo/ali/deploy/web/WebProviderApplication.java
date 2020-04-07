package study.daydayup.wolf.demo.ali.deploy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * study.daydayup.wolf.demo.ali.provider
 *
 * @author Wingle
 * @since 2019/10/11 9:49 上午
 **/
@SpringBootApplication
public class WebProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebProviderApplication.class, args);
    }
}
