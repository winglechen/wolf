package study.daydayup.wolf.business.account.deploy.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * study.daydayup.wolf.business.account.deploy.web
 *
 * @author Wingle
 * @since 2019/9/29 2:13 PM
 **/
@SpringBootApplication(scanBasePackages = {"study.daydayup.wolf.business.account"})
@MapperScan({"study.daydayup.wolf.business.account.biz.dal.dao"})
@EnableDiscoveryClient
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}
