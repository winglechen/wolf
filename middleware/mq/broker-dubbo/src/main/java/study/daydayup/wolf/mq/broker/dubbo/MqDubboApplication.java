package study.daydayup.wolf.mq.broker.dubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * study.daydayup.wolf.mq.broker.dubbo
 *
 * @author Wingle
 * @since 2019/12/3 8:27 下午
 **/
@SpringBootApplication(scanBasePackages = {"study.daydayup.wolf.mq"})
@MapperScan("study.daydayup.wolf.mq.broker.dal.dao")
@EnableDiscoveryClient
public class MqDubboApplication {
    public static void main(String[] args) {
        SpringApplication.run(MqDubboApplication.class, args);
    }
}
