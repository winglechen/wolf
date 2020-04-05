package study.daydayup.wolf.demo.my.starter.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * study.daydayup.wolf.demo.my.starter
 *
 * @author Wingle
 * @since 2019/12/4 10:53 下午
 **/
@RestController
public class StarterController {
    @RequestMapping("/starter")
    public String start() {
        return "I am start from StarterController";
    }
}
