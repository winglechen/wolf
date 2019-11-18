package study.daydayup.wolf.demo.account.deploy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@ComponentScan({"study.daydayup.wolf.demo.account", "study.daydayup.wolf.common"})
@MapperScan("study.daydayup.wolf.demo.account.biz.dal.dao")
@EnableFeignClients(basePackages = {"com.meizizi.doraemon.user"})
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
}