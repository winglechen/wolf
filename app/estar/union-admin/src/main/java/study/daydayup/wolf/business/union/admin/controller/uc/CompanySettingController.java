package study.daydayup.wolf.business.union.admin.controller.uc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.uc.agent.setting.CompanySettingAgent;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.admin.controller.uc
 *
 * @author Wingle
 * @since 2020/4/17 3:54 下午
 **/
@RestController
public class CompanySettingController {
    @Resource
    private CompanySettingAgent agent;

    @GetMapping("/uc/company/setting/set")
    public String setDemo() {
        return "ok";
    }

    @GetMapping("/uc/company/setting/get")
    public String getDemo() {
        return "ok";
    }
}
