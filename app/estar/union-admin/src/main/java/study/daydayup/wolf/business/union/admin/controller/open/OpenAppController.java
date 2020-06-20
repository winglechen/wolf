package study.daydayup.wolf.business.union.admin.controller.open;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.api.entity.OpenApp;
import study.daydayup.wolf.business.account.api.enums.OpenAppTypeEnum;
import study.daydayup.wolf.business.account.api.service.OpenAppService;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.union.admin.controller.open
 *
 * @author Wingle
 * @since 2020/6/20 4:51 下午
 **/
@RestController
public class OpenAppController implements Controller {
    @Reference
    private OpenAppService openAppService;
    @Resource
    private Session session;

    @GetMapping("/open/app/test")
    public Result<OpenApp> findTestApp() {
        Long orgId = session.get("orgId", Long.class);
        return openAppService.findTestApp(orgId);
    }

    @GetMapping("/open/app/production")
    public Result<OpenApp> findProductionApp() {
        Long orgId = session.get("orgId", Long.class);
        return openAppService.findProductionApp(orgId);
    }

    @GetMapping("/open/app/test/recreate")
    public Result<OpenApp> recreateTestApp() {
        Long orgId = session.get("orgId", Long.class);
        return openAppService.recreateApp(orgId, OpenAppTypeEnum.TEST.getCode());
    }

    @GetMapping("/open/app/production/recreate")
    public Result<OpenApp> recreateProductionApp() {
        Long orgId = session.get("orgId", Long.class);
        return openAppService.recreateApp(orgId, OpenAppTypeEnum.PRODUCTION.getCode());
    }

    @GetMapping("/open/app/all")
    public Result<List<OpenApp>> findAll() {
        Long orgId = session.get("orgId", Long.class);
        return openAppService.findAll(orgId);
    }
}
