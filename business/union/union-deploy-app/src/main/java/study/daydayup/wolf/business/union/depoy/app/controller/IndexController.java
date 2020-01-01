package study.daydayup.wolf.business.union.depoy.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.union.app.controller
 *
 * @author Wingle
 * @since 2019/12/31 7:17 下午
 **/
@RestController
@RequestMapping("/api/v1")
public class IndexController extends BaseController {

    @GetMapping("/index")
    public Result index() {
        return null;
    }
}
