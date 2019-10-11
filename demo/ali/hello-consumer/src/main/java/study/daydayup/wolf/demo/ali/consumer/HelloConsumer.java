package study.daydayup.wolf.demo.ali.consumer;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.demo.ali.api.service.HelloService;

/**
 * study.daydayup.wolf.demo.ali.consumer
 *
 * @author Wingle
 * @since 2019/10/11 10:23 上午
 **/
@RestController
public class HelloConsumer {
    @Reference
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello() {
        return helloService.sayHello("wingle");
    }
}
