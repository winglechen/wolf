package study.daydayup.wolf.business.org.biz.info.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import study.daydayup.wolf.business.org.api.info.entity.Company;
import study.daydayup.wolf.business.org.api.info.service.CompanyService;
import study.daydayup.wolf.business.uc.setting.api.entity.CompanySetting;
import study.daydayup.wolf.business.uc.setting.api.service.CompanySettingService;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.collection.ListUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.org.biz.info.api
 *
 * @author Wingle
 * @since 2020/6/23 5:05 下午
 **/
@RpcService
public class CompanyServiceImpl implements CompanyService {
    @Reference
    private CompanySettingService companySettingService;
    @Override
    public Result<List<Company>> findAll() {
        Result<List<CompanySetting>> settingResult = companySettingService.findAllDefaultSetting();
        if (CollectionUtil.isEmpty(settingResult.getData())) {
            return Result.ok(ListUtil.empty());
        }

        List<Company> companyList = new ArrayList<>();
        Company company;
        String kvString;
        JSONObject json;
        for (CompanySetting setting : settingResult.getData()) {
            kvString = setting.getData();
            if (StringUtil.isBlank(kvString)) {
                continue;
            }
            json = JSON.parseObject(kvString);
            if (StringUtil.isBlank(json.getString("name"))) {
                continue;
            }

            company = new Company();
            company.setCompanyName(json.getString("name"));
            companyList.add(company);
        }

        return Result.ok(companyList);
    }
}
