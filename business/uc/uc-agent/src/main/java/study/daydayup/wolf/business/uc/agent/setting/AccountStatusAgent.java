package study.daydayup.wolf.business.uc.agent.setting;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import study.daydayup.wolf.business.uc.agent.setting.util.StatusUtil;
import study.daydayup.wolf.business.uc.api.setting.entity.AccountStatus;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;
import study.daydayup.wolf.business.uc.api.setting.service.AccountStatusService;

import java.util.BitSet;

/**
 * study.daydayup.wolf.business.uc.agent.setting
 *
 * @author Wingle
 * @since 2020/1/1 2:47 下午
 **/
@Component
public class AccountStatusAgent {
    private static final int STATUS_LENGTH = 20;
    private boolean isInit = false;

    private long accountId;
    private BitSet statusSet;

    @Reference
    private AccountStatusService service;

    public void init(long accountId) {
        if (accountId <= 0) {
            throw new IllegalArgumentException("accountId can not less than 0");
        }

        if (isInit) {
            return;
        }

        AccountStatus status = service.find(accountId).notNullData();

        this.accountId = accountId;
        statusSet = StatusUtil.initStatus(status);

        this.isInit = true;
    }

    public boolean get(StatusEnum status) {
        int code = status.getCode();

        return statusSet.get(code);
    }

    public AccountStatusAgent set(StatusEnum status) {
        return set(status, true);
    }

    public AccountStatusAgent set(StatusEnum status, boolean state) {
        int code = status.getCode();
        statusSet.set(code, state);

        return this;
    }

    public void save() {
        long[] sArray = statusSet.toLongArray();
        AccountStatus status = arrayToModel(sArray);

        service.save(status);
    }

    private AccountStatus arrayToModel(long[] s) {
        if (s.length != STATUS_LENGTH + 1) {
            throw new IllegalArgumentException("invalid status array format");
        }

        AccountStatus status = new AccountStatus();
        status.setAccountId(accountId);

        long[] sArray = StatusUtil.formatBitArray(s, STATUS_LENGTH);
        StatusUtil.setStatus(status, sArray);

        return status;
    }
}
