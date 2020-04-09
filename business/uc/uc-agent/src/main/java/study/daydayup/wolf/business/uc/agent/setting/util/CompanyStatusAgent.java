package study.daydayup.wolf.business.uc.agent.setting.util;

import org.apache.dubbo.config.annotation.Reference;
import study.daydayup.wolf.business.uc.api.setting.entity.CompanyStatus;
import study.daydayup.wolf.business.uc.api.setting.enums.StatusEnum;
import study.daydayup.wolf.business.uc.api.setting.service.CompanyStatusService;

import java.util.BitSet;

/**
 * study.daydayup.wolf.business.uc.agent.setting
 *
 * @author Wingle
 * @since 2020/1/1 2:47 下午
 **/
public class CompanyStatusAgent {
    private static final int STATUS_LENGTH = 20;
    private boolean isInit = false;

    private long orgId;
    private BitSet statusSet;

    @Reference
    private CompanyStatusService service;

    public void init(long orgId) {
        if (orgId <= 0) {
            throw new IllegalArgumentException("orgId can not less than 0");
        }

        if (isInit) {
            return;
        }

        CompanyStatus status = service.find(orgId).notNullData();

        this.orgId = orgId;
        statusSet = StatusUtil.initStatus(status);

        this.isInit = true;
    }

    public boolean get(StatusEnum status) {
        int code = status.getCode();

        return statusSet.get(code);
    }

    public CompanyStatusAgent set(StatusEnum status) {
        return set(status, true);
    }

    public CompanyStatusAgent set(StatusEnum status, boolean state) {
        int code = status.getCode();
        statusSet.set(code, state);

        return this;
    }

    public void save() {
        long[] sArray = statusSet.toLongArray();
        CompanyStatus status = arrayToModel(sArray);

        service.save(status);
    }

    private CompanyStatus arrayToModel(long[] s) {
        if (s.length != STATUS_LENGTH + 1) {
            throw new IllegalArgumentException("invalid status array format");
        }

        CompanyStatus status = new CompanyStatus();
        status.setOrgId(orgId);

        long[] sArray = StatusUtil.formatBitArray(s, STATUS_LENGTH);
        StatusUtil.setStatus(status, sArray);

        return status;
    }
}
