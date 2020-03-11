package study.daydayup.wolf.business.union.admin.controller.customer;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditConfig;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.entity.CreditLine;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.service.CreditConfigService;
import study.daydayup.wolf.business.uc.api.crm.customer.credit.service.CreditLineService;
import study.daydayup.wolf.business.union.admin.dto.CreditAmount;
import study.daydayup.wolf.common.util.collection.CollectionUtil;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

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
    @Reference
    private CreditLineService creditService;
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

    @PutMapping("/customer/credit/promote")
    public Result<Integer> promote(@Validated @RequestBody CreditAmount creditAmount) {
        Long orgId = session.get("orgId", Long.class);
        Long accountId = creditAmount.getAccountId();
        BigDecimal amount = creditAmount.getAmount();

        return creditService.promote(accountId, orgId, amount);
    }

    @PutMapping("/customer/credit/demote")
    public Result<Integer> demote(@Validated @RequestBody CreditAmount creditAmount) {
        Long orgId = session.get("orgId", Long.class);
        Long accountId = creditAmount.getAccountId();
        BigDecimal amount = creditAmount.getAmount();

        return creditService.demote(accountId, orgId, amount);
    }

    @GetMapping("/customer/credit/creditLine/{accountId}")
    public Result<CreditLine> find(@PathVariable("accountId") Long accountId) {
        Long orgId = session.get("orgId", Long.class);

        return creditService.find(accountId, orgId);
    }

    @GetMapping("/customer/credit/creditLine/list/accountIds")
    public Result<List<CreditLine>> findAccounts(@RequestParam("accountIds") Collection<Long> accountIds) {
        if (CollectionUtil.isEmpty(accountIds)) {
            return Result.fail(10000, "invalid accountIds");
        }

        Long orgId = session.get("orgId", Long.class);
        return creditService.findByAccounts(accountIds, orgId);
    }
    @GetMapping("/customer/credit/creditLine/account/{accountId}")
    public Result<Page<CreditLine>> findByAccount(@PathVariable("accountId") Long accountId, @RequestParam(value = "pageNum", required = false) Integer pageNum) {
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return creditService.findByAccount(accountId, pageRequest);
    }

    @GetMapping("/customer/credit/creditLine/list")
    public Result<Page<CreditLine>> findByOrg(@RequestParam(value = "pageNum", required = false) Integer pageNum) {
        Long orgId = session.get("orgId", Long.class);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == pageNum ? 1 : pageNum)
                .pageSize(10)
                .build();

        return creditService.findByOrg(orgId, pageRequest);
    }





}
