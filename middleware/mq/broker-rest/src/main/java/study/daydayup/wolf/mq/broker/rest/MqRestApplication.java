package study.daydayup.wolf.mq.broker.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * study.daydayup.wolf.mq.broker.rest
 *
 * @author Wingle
 * @since 2019/12/3 8:27 下午
 **/
@SpringBootApplication(scanBasePackages = {"study.daydayup.wolf.mq"})
@EnableDiscoveryClient
public class MqRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqRestApplication.class, args);
    }
}
