package study.daydayup.wolf.business.union.deploy.web.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.union.api.UnionDemoService;

/**
 * study.daydayup.wolf.business.union.deploy.web.controller
 *
 * @author Wingle
 * @since 2019/12/10 7:16 下午
 **/
@RestController
public class UnionDemoController {
    @Reference
    private UnionDemoService demoService;

    @RequestMapping("/show")
    public String show() {
        String show = demoService.show();

        return show;
    }
}
