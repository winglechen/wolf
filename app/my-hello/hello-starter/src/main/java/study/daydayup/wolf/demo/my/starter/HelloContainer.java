package study.daydayup.wolf.demo.my.starter;

import lombok.Data;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.demo.my.starter
 *
 * @author Wingle
 * @since 2019/12/4 9:55 下午
 **/
@Data
public class HelloContainer {
    @Resource
    HelloService helloService;
}
