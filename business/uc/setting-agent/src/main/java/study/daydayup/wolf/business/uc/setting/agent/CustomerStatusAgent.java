package study.daydayup.wolf.business.uc.setting.agent;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.setting.agent.util.StatusUtil;
import study.daydayup.wolf.business.uc.setting.api.entity.CustomerStatus;
import study.daydayup.wolf.business.uc.setting.api.enums.StatusEnum;
import study.daydayup.wolf.business.uc.setting.api.enums.customer.CustomerStatusGroupEnum;
import study.daydayup.wolf.business.uc.setting.api.enums.customer.CustomerStatusProgressEnum;
import study.daydayup.wolf.business.uc.setting.api.service.CustomerStatusService;
import study.daydayup.wolf.common.model.type.number.Step;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.util.*;

/**
 * study.daydayup.wolf.business.uc.agent.setting
 *
 * @author Wingle
 * @since 2020/1/1 2:47 下午
 **/
@Slf4j
@Component
@Scope("prototype")
public class CustomerStatusAgent {
    private static final int STATUS_LENGTH = 20;
    private boolean isInit = false;

    private long accountId;
    private long orgId;

    private BitSet statusSet;
    private Map<StatusEnum, StatusEnum> progressMap;
    private Map<StatusEnum, List<StatusEnum>> progressStep;

    @Reference
    private CustomerStatusService customerStatusService;

    public void init(long accountId, long orgId) {
        if (accountId <= 0 || orgId <= 0) {
            throw new IllegalArgumentException("accountId and orgId can not less than 0: accountId= " + accountId + "; orgId= " + orgId);
        }

//        if (isInit) {
//            return;
//        }

        CustomerStatus status = customerStatusService.find(accountId, orgId).notNullData();

        this.accountId = accountId;
        this.orgId = orgId;
        statusSet = StatusUtil.initStatus(status);
        initStatusMap();

        this.isInit = true;
    }

    public boolean get(StatusEnum status) {
        int code = status.getCode();

        return statusSet.get(code);
    }

    public Map<String, Boolean> getGroup(CustomerStatusGroupEnum... groups) {
        Map<String, Boolean> map = new HashMap<>();

        for (CustomerStatusGroupEnum group: groups) {
            map.putAll(getGroup(group));
        }

        return map;
    }

    public Map<String, Boolean> getGroup(CustomerStatusGroupEnum group) {
        if (group == null) {
            return null;
        }

        Class<? extends StatusEnum> clazz = group.getGroup();
        Map<String, Boolean> map = new HashMap<>();

        for(StatusEnum status: clazz.getEnumConstants()) {
            String desc = status.getName();
            map.put(StringUtil.camel(desc, "."), get(status));
        }

        return map;
    }

    public CustomerStatusAgent set(StatusEnum status) {
        return set(status, true);
    }

    public CustomerStatusAgent set(StatusEnum status, boolean state) {
        int code = status.getCode();
        statusSet.set(code, state);

        updateProgress(status, state);
        return this;
    }

    public Step getProgress(@NonNull StatusEnum status) {
        if (null == progressStep.get(status)) {
            return null;
        }


        int total = 0, current = 0;
        for (StatusEnum step : progressStep.get(status)) {
            total++;
            if (get(step)) {
                current++;
            }
        }

        return Step.of(current, total);
    }

    private void updateProgress(StatusEnum status, boolean state) {
        StatusEnum progress = progressMap.get(status);
        if (null == progress) {
            return;
        }

        for (StatusEnum step : progressStep.get(progress)) {
            if (get(step) != state) {
                return;
            }
        }

        set(progress, state);
    }

    public void save() {
        long[] sArray = statusSet.toLongArray();
        CustomerStatus status = arrayToModel(sArray);

        customerStatusService.save(status);
    }

    private void initStatusMap() {
        if (progressMap != null) {
            return;
        }

        progressMap = new HashMap<>(16);
        progressStep = new HashMap<>(8);
        List<StatusEnum> progress;
        for (CustomerStatusProgressEnum s : CustomerStatusProgressEnum.class.getEnumConstants()) {
            progressMap.put(s.getStatus(), s.getProgress());

            progress = progressStep.computeIfAbsent(s.getProgress(), k -> new LinkedList<>());
            progress.add(s.getStatus());
        }
    }

    private CustomerStatus arrayToModel(long[] s) {
        if (s.length != STATUS_LENGTH + 1) {
            throw new IllegalArgumentException("invalid status array format");
        }

        CustomerStatus status = new CustomerStatus();
        status.setAccountId(accountId);
        status.setOrgId(orgId);

        long[] sArray = StatusUtil.formatBitArray(s, STATUS_LENGTH);
        StatusUtil.setStatus(status, sArray);

        return status;
    }

}
