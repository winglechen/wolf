package study.daydayup.wolf.demo.my.stater.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.demo.my.starter.DemoService;
import study.daydayup.wolf.demo.my.starter.HelloContainer;
import study.daydayup.wolf.demo.my.starter.HelloService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.demo.my.stater.web
 *
 * @author Wingle
 * @since 2019/12/4 9:08 下午
 **/
@RestController
public class HelloController {
    @Resource
    private HelloContainer helloContainer;

    @Resource
    private DemoService demoService;


    @RequestMapping("/show")
    public String show() {
        HelloService helloService = helloContainer.getHelloService();
        return helloService.sayHello();
    }

    @RequestMapping("/demo")
    public String demo() {
        demoService.show();
        return "demo";
    }
}
