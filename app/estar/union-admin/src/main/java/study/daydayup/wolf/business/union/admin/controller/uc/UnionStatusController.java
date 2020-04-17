package study.daydayup.wolf.business.union.admin.controller.uc;

import lombok.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.daydayup.wolf.business.uc.agent.setting.CustomerStatusAgent;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;
import study.daydayup.wolf.business.uc.api.setting.enums.customer.*;
import study.daydayup.wolf.business.union.admin.controller.BaseUnionController;
import study.daydayup.wolf.common.util.collection.MapUtil;
import study.daydayup.wolf.common.util.lang.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
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
        agent.set(TradeTagEnum.LOANING);
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

    @GetMapping("/status/all")
    public Map<String, Boolean> all() {
        agent.init(session.get("accountId", Long.class), session.get("orgId", Long.class));

        return agent.getGroup(CustomerStatusGroupEnum.TRADE_TAG
                , CustomerStatusGroupEnum.CUSTOMER_INFO
                , CustomerStatusGroupEnum.CUSTOMER_AUTH
                , CustomerStatusGroupEnum.CUSTOMER_TAG
        );
    }

    @PutMapping("/status/mock")
    public String mock(@RequestBody Map<String, Object> status) {
        if (MapUtil.isEmpty(status)) {
            return "empty status put";
        }

        Map<String, StatusEnum> statusMap = new HashMap<>();
        initCustomerInfo(statusMap);
        initCustomerAuth(statusMap);
        initCustomerTag(statusMap);
        initTradeTag(statusMap);

        agent.init(session.get("accountId", Long.class), session.get("orgId", Long.class));

        StatusEnum s;
        String key;
        for (Map.Entry<String, Object> entry : status.entrySet()) {
            key = entry.getKey();
            if (null == key) {
                continue;
            }

            if (!key.contains(".")) {
                key = StringUtil.camelTo(key, ".");
            }

            s = statusMap.get(key);
            if (s == null || !(entry.getValue() instanceof Boolean) ) {
                continue;
            }
            agent.set(s, (Boolean) entry.getValue());
        }

        agent.save();

        return "status changed";
    }

    private void initCustomerInfo(@NonNull Map<String, StatusEnum> statusMap) {
        for (CustomerInfoEnum e : CustomerInfoEnum.values()) {
            statusMap.put(e.getName(), e);
        }
    }

    private void initCustomerAuth(@NonNull Map<String, StatusEnum> statusMap) {
        for (CustomerAuthEnum e : CustomerAuthEnum.values()) {
            statusMap.put(e.getName(), e);
        }
    }

    private void initCustomerTag(@NonNull Map<String, StatusEnum> statusMap) {
        for (CustomerTagEnum e : CustomerTagEnum.values()) {
            statusMap.put(e.getName(), e);
        }
    }

    private void initTradeTag(@NonNull Map<String, StatusEnum> statusMap) {
        for (TradeTagEnum e : TradeTagEnum.values()) {
            statusMap.put(e.getName(), e);
        }
    }
}
