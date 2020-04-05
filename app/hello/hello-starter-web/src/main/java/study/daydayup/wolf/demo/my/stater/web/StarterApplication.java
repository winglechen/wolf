package study.daydayup.wolf.demo.my.stater.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * study.daydayup.wolf.demo.my.stater.web
 *
 * @author Wingle
 * @since 2019/12/4 9:07 下午
 **/
@SpringBootApplication
public class StarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class, args);
    }
}
