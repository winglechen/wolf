package study.daydayup.wolf.demo.ali.consumer;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.service.GenericService;
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

    @RequestMapping("/generic")
    public String generic() {
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("study.daydayup.wolf.demo.ali.api.service.HelloService");
        reference.setGeneric(true);

        GenericService genericService = reference.get();
        Object result = genericService.$invoke("sayHello", new String[]{"java.lang.String"}, new Object[]{"world"});


        return  "generic result: " + (String) result;
    }
}
