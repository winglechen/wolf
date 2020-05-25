package study.daydayup.wolf.business.uc.crm.biz.staff.info.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Reference;
import study.daydayup.wolf.business.uc.api.crm.staff.dto.CompanyDTO;
import study.daydayup.wolf.business.uc.api.crm.staff.service.info.AdminService;
import study.daydayup.wolf.business.uc.setting.api.entity.CompanySetting;
import study.daydayup.wolf.business.uc.setting.api.service.CompanySettingService;
import study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dao.AdminDAO;
import study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dataobject.AdminDO;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * study.daydayup.wolf.business.uc.crm.biz.staff.info.api
 *
 * @author Wingle
 * @since 2020/4/22 10:01 下午
 **/
@RpcService(protocol = "dubbo")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDAO adminDAO;
    @Reference
    private CompanySettingService companySettingService;

    @Override
    public Result<Page<CompanyDTO>> findCompanies(Long accountId, PageRequest pageReq) {
        Page.startPage(pageReq.getPageNum(), pageReq.getPageSize());

        List<AdminDO> adminDOList = adminDAO.selectByAccountId(accountId);
        if (CollectionUtil.isEmpty(adminDOList)) {
            return Result.ok(Page.empty(pageReq.getPageNum(), pageReq.getPageSize()));
        }

        return Result.ok(mergeCompanyInfo(adminDOList));
    }

    private Page<CompanyDTO> mergeCompanyInfo(List<AdminDO> adminDOList) {
        List<Long> orgIds = CollectionUtil.keys(adminDOList, AdminDO::getOrgId);
        List<CompanySetting> settingList = companySettingService.findByOrgIds(orgIds).notNullData();

        List<CompanyDTO> companyDTOList = new ArrayList<>(settingList.size());
        for (CompanySetting setting : settingList) {
            CompanyDTO companyDTO = CompanyDTO.builder()
                    .orgId(setting.getOrgId())
                    .build();

            companyDTOList.add(companyDTO);
            if (StringUtil.isBlank(setting.getData())) {
                continue;
            }

            JSONObject data = JSON.parseObject(setting.getData());
            companyDTO.setName(data.getString("name"));
            companyDTO.setLogo(data.getString("logo"));
        }

        return Page.of(adminDOList).to(companyDTOList);
    }
}
