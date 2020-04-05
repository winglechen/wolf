package study.daydayup.wolf.demo.my.starter.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * study.daydayup.wolf.demo.my.starter
 *
 * @author Wingle
 * @since 2019/12/4 10:43 下午
 **/
@Component
public class DemoService {
    public void show() {
        System.out.println("I am demoService from hello stater");
    }
}
