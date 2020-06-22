package study.daydayup.wolf.business.union.admin.controller.pay;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.account.auth.agent.Session;
import study.daydayup.wolf.business.pay.api.domain.entity.Settlement;
import study.daydayup.wolf.business.pay.api.dto.base.manage.SettlementQuery;
import study.daydayup.wolf.business.pay.api.service.SettlementService;
import study.daydayup.wolf.framework.layer.web.Controller;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.union.admin.controller.pay
 *
 * @author Wingle
 * @since 2020/6/22 11:40 上午
 **/
@RestController
public class SettlementController implements Controller {
    @Reference
    private SettlementService settlementService;
    @Resource
    private Session session;

    @GetMapping("/pay/settlement/query")
    public Result<Page<Settlement>> find(@Validated @ModelAttribute SettlementQuery query) {
        Long orgId = session.get("orgId", Long.class);
        query.setAccountId(orgId);
        PageRequest pageRequest = PageRequest.builder()
                .pageNum(null == query.getPageNum() ? 1 : query.getPageNum())
                .pageSize(10)
                .build();

        return settlementService.query(query, pageRequest);
    }
}
