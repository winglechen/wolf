package study.daydayup.wolf.business.union.admin.controller.uc;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.uc.agent.setting.CompanySettingAgent;
import study.daydayup.wolf.business.uc.api.setting.entity.CompanySetting;
import study.daydayup.wolf.business.uc.api.setting.service.CompanySettingService;
import study.daydayup.wolf.common.lang.ds.ObjectMap;
import study.daydayup.wolf.common.util.collection.ListUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
    @Reference
    private CompanySettingService service;

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

    @PutMapping("/uc/company/setting/{orgId}/{namespace}")
    public String mockByNamespace(@PathVariable("orgId") Long orgId, @PathVariable("namespace") String namespace, @RequestBody Map<String, Object> setting) {
        agent.init(orgId, false);
        agent.namespace(namespace);

        ObjectMap map = agent.getAll();
        return JSON.toJSONString(map);
    }

    @PutMapping("/uc/company/setting/{orgId}")
    public String mockOrg(@PathVariable("orgId") Long orgId,@RequestBody Map<String, Object> setting) {
        agent.init(orgId, false);

        agent.setAll(setting);
        agent.save();

        ObjectMap map = agent.getAll();
        return JSON.toJSONString(map);
    }

    @GetMapping("/uc/company/findByOrgIds")
    public Result<List<CompanySetting>> byOrgIds(@RequestParam("orgIds") String orgIdStr, @RequestParam("namespace") String namespace) {
        if (StringUtil.isBlank(orgIdStr)) {
            throw new IllegalArgumentException("orgIds can't be blank");
        }

        String[] orgArr = StringUtil.split(orgIdStr, ",");
        if (orgArr.length == 0) {
            return Result.ok(ListUtil.empty());
        }

        List<Long> orgIds = new ArrayList<>(8);
        Long orgId;
        for (String s : orgArr) {
            orgId = Long.valueOf(s);
            if (orgId <= 0) {
                continue;
            }

            orgIds.add(orgId);
        }

        return service.findByOrgIds(namespace, orgIds);
    }




}
