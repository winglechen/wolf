package study.daydayup.wolf.demo.my.stater.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.demo.my.starter.DemoService;
import study.daydayup.wolf.demo.my.starter.HelloContainer;
import study.daydayup.wolf.demo.my.starter.HelloService;
import study.daydayup.wolf.demo.my.stater.web.flow.TradeFlow;
import study.daydayup.wolf.demo.my.stater.web.flow.TradeType;

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

    @Resource
    private TradeType tradeType;
    @Resource
    private TradeFlow tradeFlow;


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

    @RequestMapping("/flow")
    public String flow(@RequestParam Integer type) {
        if (type == null) {
            return "no type!";
        }

        if (type.equals(1)) {
            tradeType.type = 1;
            return tradeFlow.execute();
        }

        tradeType.type = 2;
        return tradeFlow.execute();

    }
}
