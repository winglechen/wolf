package study.daydayup.wolf.business.union.task.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * study.daydayup.wolf.business.union.task.controller
 *
 * @author Wingle
 * @since 2020/2/3 8:12 下午
 **/
@RestController
public class IndexController {
    @RequestMapping("/index/hello")
    public String hello() {
        return "Hello union task";
    }

}
