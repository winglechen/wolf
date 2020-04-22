package study.daydayup.wolf.business.union.admin.controller.uc;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.uc.agent.setting.CompanySettingAgent;
import study.daydayup.wolf.common.lang.ds.ObjectMap;

import javax.annotation.Resource;
import java.util.Map;

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
    @Resource
    private Session session;

    @GetMapping("/uc/company/demo")
    public String demo() {
        Long orgId = session.get("orgId", Long.class);
        agent.init(orgId);

        agent.namespace("info");
        agent.set("name", "repeat company name");
        agent.set("age", 10);
        agent.set("address", "HangZhou");

        agent.save();

        ObjectMap map = agent.getAll();
        return JSON.toJSONString(map);
    }

    @GetMapping("/uc/company/setting/{namespace}")
    public String findByNamespace(@PathVariable("namespace") String namespace) {
        Long orgId = session.get("orgId", Long.class);
        agent.init(orgId);
        agent.namespace(namespace);

        ObjectMap map = agent.getAll();
        return JSON.toJSONString(map);
    }

    @PutMapping("/uc/company/setting/{namespace}")
    public String mockByNamespace(@PathVariable("namespace") String namespace, @RequestBody Map<String, Object> setting) {
        Long orgId = session.get("orgId", Long.class);
        agent.init(orgId);
        agent.namespace(namespace);

        ObjectMap map = agent.getAll();
        return JSON.toJSONString(map);
    }

    @PutMapping("/uc/company/setting")
    public String mockOrg(@RequestBody Map<String, Object> setting) {
        Long orgId = session.get("orgId", Long.class);
        agent.init(orgId);

        agent.setAll(setting);
        agent.save();

        ObjectMap map = agent.getAll();
        return JSON.toJSONString(map);
    }




}
