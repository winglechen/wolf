package study.daydayup.wolf.business.union.admin.controller.customer;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.service.CreditConfigService;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.admin.controller.customer
 *
 * @author Wingle
 * @since 2020/3/11 6:55 下午
 **/
@RestController
public class CreditLineController implements Controller {
    @Reference
    private CreditConfigService configService;
    @Resource
    private Session session;

    @GetMapping("/customer/credit/config")
    public Result<CreditConfig> findConfig() {
        Long orgId = session.get("orgId", Long.class);

        return configService.find(orgId);
    }

    @PutMapping("/customer/credit/config")
    public Result<Integer> saveConfig(@Validated @RequestBody CreditConfig config) {
        Long orgId = session.get("orgId", Long.class);
        config.setOrgId(orgId);

        return configService.save(config);
    }

}
