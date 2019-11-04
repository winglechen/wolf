package study.daydayup.wolf.demo.ali.deploy.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * study.daydayup.wolf.demo.ali.deploy.web
 *
 * @author Wingle
 * @since 2019/11/4 3:03 下午
 **/
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello provider";
    }
}
