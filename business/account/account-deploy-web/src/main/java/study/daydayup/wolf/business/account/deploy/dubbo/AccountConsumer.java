package study.daydayup.wolf.business.account.deploy.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * study.daydayup.wolf.business.account.deploy.web
 *
 * @author Wingle
 * @since 2019/9/29 2:13 PM
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class AccountConsumer {
    public static void main(String[] args) {
        SpringApplication.run(AccountConsumer.class, args);
    }
}
