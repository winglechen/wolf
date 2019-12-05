package study.daydayup.wolf.business.account.auth.agent.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.account.auth.agent.controller
 *
 * @author Wingle
 * @since 2019/12/4 5:49 下午
 **/
@RestController
public class LogoutController extends AuthController {

    @RequestMapping("/auth/logout")
    public Result logout() {
       return Result.ok();
    }
}
