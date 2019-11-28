package study.daydayup.wolf.business.account.deploy.dubbo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.api.agent.AccountAgent;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.account.deploy.dubbo.controller
 *
 * @author Wingle
 * @since 2019/11/28 9:48 上午
 **/
@RestController
public class AgentController {
    @Resource
    private AccountAgent agent;

    @RequestMapping("/agent")
    public String show() {
        long result = agent.create();
        return "agent service return : <" + result + ">";
    }
}
