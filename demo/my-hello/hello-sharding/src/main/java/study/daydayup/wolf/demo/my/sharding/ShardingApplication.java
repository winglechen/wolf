package study.daydayup.wolf.demo.my.sharding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * study.daydayup.wolf.demo.my.sharding
 *
 * @author Wingle
 * @since 2019/11/13 7:19 下午
 **/
@MapperScan(basePackages = "study.daydayup.wolf.demo.my.sharding.dal")
@SpringBootApplication
@RestController
public class ShardingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingApplication.class, args);
    }

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("hello from sharding application");
        return "hello world";
    }
}
