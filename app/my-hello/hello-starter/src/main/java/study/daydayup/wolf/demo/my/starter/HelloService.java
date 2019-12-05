package study.daydayup.wolf.demo.my.starter;

import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.UUID;

/**
 * study.daydayup.wolf.demo.my.starter
 *
 * @author Wingle
 * @since 2019/12/4 8:50 下午
 **/
@Setter
public class HelloService {
    private String msg;
    private boolean show = true;
    private String uuid;

    @Resource
    private HelloProperties config;

    @PostConstruct
    public void initMethod() {
        uuid = UUID.randomUUID().toString();
        System.out.println("I am before construct");
    }

    public String sayHello() {
        System.out.println("config:" + config.getMsg());
        return "Hello, " + msg + "; uuid: " + uuid;
//        return show ? "Hello, " + msg : "Hidden";
    }

    @PreDestroy
    public void destroyMethod() {
        System.out.println("I am destroying ...");
    }
}
