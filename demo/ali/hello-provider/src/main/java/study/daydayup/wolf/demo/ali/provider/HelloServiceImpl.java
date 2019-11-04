package study.daydayup.wolf.demo.ali.provider;

import org.apache.dubbo.config.annotation.Service;
import study.daydayup.wolf.demo.ali.api.service.HelloService;

/**
 * study.daydayup.wolf.demo.ali.provider
 *
 * @author Wingle
 * @since 2019/10/11 9:59 上午
 **/
@Service(protocol = "dubbo")
public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return "Hello " + name + " from dubbo service: " + this.toString();
    }
}
