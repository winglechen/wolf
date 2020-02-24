package study.daydayup.wolf.business.account.auth.agent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.account.auth.agent.controller
 *
 * @author Wingle
 * @since 2020/2/25 12:05 上午
 **/
@RestController
public class LangController {
    @GetMapping("/auth/lang")
    public Result<String> lang() {
        return Result.ok("ok");
    }
}
