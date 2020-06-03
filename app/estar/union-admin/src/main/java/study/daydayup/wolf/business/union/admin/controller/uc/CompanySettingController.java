package study.daydayup.wolf.business.union.admin.controller.uc;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.uc.setting.agent.CompanySettingAgent;
import study.daydayup.wolf.business.uc.setting.api.entity.CompanySetting;
import study.daydayup.wolf.business.uc.setting.api.service.CompanySettingService;
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
    private CompanySettingAgent companySettingAgent;
    @Resource
    private Session session;
    @Reference
    private CompanySettingService companySettingService;

    @GetMapping("/uc/company/demo")
    public String demo() {
        Long orgId = session.get("orgId", Long.class);
        companySettingAgent.init(orgId);

        companySettingAgent.namespace("info");
        companySettingAgent.set("name", "repeat company name");
        companySettingAgent.set("age", 10);
        companySettingAgent.set("address", "HangZhou");

        companySettingAgent.save();

        ObjectMap map = companySettingAgent.getAll();
        return JSON.toJSONString(map);
    }

    @GetMapping("/uc/company/setting/{namespace}")
    public String findByNamespace(@PathVariable("namespace") String namespace) {
        Long orgId = session.get("orgId", Long.class);
        companySettingAgent.init(orgId);
        companySettingAgent.namespace(namespace);

        ObjectMap map = companySettingAgent.getAll();
        return JSON.toJSONString(map);
    }

    @PutMapping("/uc/company/setting/{orgId}/{namespace}")
    public String mockByNamespace(@PathVariable("orgId") Long orgId, @PathVariable("namespace") String namespace, @RequestBody Map<String, Object> setting) {
        companySettingAgent.init(orgId, false);
        companySettingAgent.namespace(namespace);

        companySettingAgent.setAll(setting);
        companySettingAgent.save();

        ObjectMap map = companySettingAgent.getAll();
        return JSON.toJSONString(map);
    }

    @PutMapping("/uc/company/setting/{orgId}")
    public String mockOrg(@PathVariable("orgId") Long orgId,@RequestBody Map<String, Object> setting) {
        companySettingAgent.init(orgId, false);

        companySettingAgent.setAll(setting);
        companySettingAgent.save();

        ObjectMap map = companySettingAgent.getAll();
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

        return companySettingService.findByOrgIds(namespace, orgIds);
    }




}
