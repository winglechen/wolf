package study.daydayup.wolf.business.union.depoy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * study.daydayup.wolf.business.union.depoy.app
 *
 * @author Wingle
 * @since 2020/1/1 5:24 下午
 **/
@SpringBootApplication(scanBasePackages = {"study.daydayup.wolf.business.union", "net.onionpay.gateway"})
@EnableDiscoveryClient
public class UnionAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnionAppApplication.class, args);
    }
}
