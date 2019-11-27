package study.daydayup.wolf.demo.ali.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * study.daydayup.wolf.demo.ali.consumer
 *
 * @author Wingle
 * @since 2019/10/11 10:31 上午
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
