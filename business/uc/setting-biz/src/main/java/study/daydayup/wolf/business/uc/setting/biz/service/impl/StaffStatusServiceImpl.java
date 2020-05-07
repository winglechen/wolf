package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.setting.api.entity.StaffStatus;
import study.daydayup.wolf.business.uc.setting.api.service.StaffStatusService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.StaffStatusDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.StaffStatusDO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:39 下午
 **/
@RpcService(protocol = "dubbo")
public class StaffStatusServiceImpl implements StaffStatusService {
    @Resource
    private StaffStatusDAO dao;

    @Override
    public Result<StaffStatus> find(@NonNull Long accountId, @NonNull Long orgId) {
        StaffStatusDO customerStatusDO = dao.findByAccountId(accountId, orgId);
        if (customerStatusDO == null) {
            return initStatus(accountId, orgId);
        }
        StaffStatus status = DOToModel(customerStatusDO);
        return Result.ok(status);
    }

    @Override
    public Result<Integer> save(@Validated StaffStatus customerStatus) {
        int status;
        StaffStatusDO customerStatusDO = dao.findByAccountId(customerStatus.getAccountId(), customerStatus.getOrgId());
        if (customerStatusDO == null) {
            status = dao.insertSelective(modelToDO(customerStatus));
            return Result.ok(status);
        }

        status = dao.updateByAccountId(modelToDO(customerStatus), customerStatus.getAccountId(), customerStatus.getOrgId());
        return Result.ok(status);
    }

    private Result<StaffStatus> initStatus(Long accountId, Long orgId) {
        StaffStatus status = new StaffStatus();
        status.setAccountId(accountId);
        status.setOrgId(orgId);

        StaffStatusDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return Result.ok(status);
    }

    private StaffStatus DOToModel(StaffStatusDO customerStatusDO) {
        if (customerStatusDO == null) {
            return null;
        }

        StaffStatus customerStatus = new StaffStatus();
        BeanUtils.copyProperties(customerStatusDO, customerStatus);

        return customerStatus;
    }

    private StaffStatusDO modelToDO(StaffStatus customerStatus) {
        if (customerStatus == null) {
            return null;
        }

        StaffStatusDO customerStatusDO = new StaffStatusDO();
        BeanUtils.copyProperties(customerStatus, customerStatusDO);

        return customerStatusDO;
    }

}
