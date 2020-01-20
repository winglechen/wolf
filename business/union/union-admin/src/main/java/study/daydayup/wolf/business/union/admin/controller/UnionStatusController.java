package study.daydayup.wolf.business.union.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.uc.agent.setting.CustomerStatusAgent;
import study.daydayup.wolf.business.uc.api.setting.enums.customer.CustomerStatusGroupEnum;
import study.daydayup.wolf.business.uc.api.setting.enums.customer.TradeTagEnum;

import javax.annotation.Resource;
import java.util.Map;

/**
 * study.daydayup.wolf.business.union.admin.controller
 *
 * @author Wingle
 * @since 2020/1/2 9:22 上午
 **/
@RestController
public class UnionStatusController extends BaseUnionController {
    @Resource
    private CustomerStatusAgent agent;

    @GetMapping("/status/show")
    public Map<String, Boolean> show() {
        agent.init(getFromSession("accountId", Long.class), getFromSession("orgId", Long.class));

        Map<String, Boolean> tradeStatus = agent.getGroup(CustomerStatusGroupEnum.TRADE_TAG);
        return tradeStatus;
    }

    @GetMapping("/status/show/step1")
    public Map<String, Boolean> step1() {
        agent.init(getFromSession("accountId", Long.class), getFromSession("orgId", Long.class));

        agent.set(TradeTagEnum.FIRST_TRADE);
        agent.set(TradeTagEnum.LOAN_LOANING);
        agent.save();

        Map<String, Boolean> tradeStatus = agent.getGroup(CustomerStatusGroupEnum.TRADE_TAG);
        return tradeStatus;
    }

    @GetMapping("/status/show/step2")
    public Map<String, Boolean> step2() {
        agent.init(getFromSession("accountId", Long.class), getFromSession("orgId", Long.class));

        Map<String, Boolean> tradeStatus = agent.getGroup(CustomerStatusGroupEnum.TRADE_TAG);
        return tradeStatus;
    }

}
