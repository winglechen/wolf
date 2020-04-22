package study.daydayup.wolf.business.union.admin.controller.uc;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.uc.api.crm.staff.dto.CompanyDTO;
import study.daydayup.wolf.business.uc.api.crm.staff.service.info.AdminService;
import study.daydayup.wolf.business.union.common.config.DomainConfig;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.admin.controller.uc
 *
 * @author Wingle
 * @since 2020/4/22 11:11 下午
 **/
@RestController
public class AdminController {
    @Resource
    private Session session;
    @Reference
    private AdminService adminService;
    @Resource
    private DomainConfig domainConfig;

    @GetMapping("/uc/staff/admin/company")
    Result<Page<CompanyDTO>> findCompanies(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long staffId = session.get("accountId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        Page<CompanyDTO> companyList = adminService.findCompanies(staffId, pageRequest).getData();
        formatDomainUrl(companyList);

        return Result.ok(companyList);
    }

    private void formatDomainUrl(Page<CompanyDTO> companyList) {
        if (null == companyList) {
            return;
        }

        String baseUrl = "https://s.onionwallet.net";
        for (CompanyDTO company : companyList.getData()) {
            if (StringUtil.isBlank(company.getLogo())) {
                continue;
            }

            company.setLogo(StringUtil.join(baseUrl, company.getLogo()));
        }
    }
}
