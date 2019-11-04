package study.daydayup.wolf.demo.ali.consumer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * study.daydayup.wolf.demo.ali.consumer
 *
 * @author Wingle
 * @since 2019/10/11 10:23 上午
 **/
@RestController
public class GenericConsumer {

    @RequestMapping("/generic")
    public Object hello() {
        return "Hello";
    }
}
