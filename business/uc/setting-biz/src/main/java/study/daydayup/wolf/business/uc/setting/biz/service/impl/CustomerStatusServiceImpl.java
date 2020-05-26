package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.setting.api.entity.CustomerStatus;
import study.daydayup.wolf.business.uc.setting.api.service.CustomerStatusService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.CustomerStatusDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CustomerStatusDO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:39 下午
 **/
@RpcService
public class CustomerStatusServiceImpl implements CustomerStatusService {
    @Resource
    private CustomerStatusDAO dao;

    @Override
    public Result<CustomerStatus> find(@NonNull Long accountId, @NonNull Long orgId) {
        CustomerStatusDO customerStatusDO = dao.findByAccountId(accountId, orgId);
        if (customerStatusDO == null) {
            return initStatus(accountId, orgId);
        }
        CustomerStatus status = DOToModel(customerStatusDO);
        return Result.ok(status);
    }

    @Override
    public Result<Integer> save(@Validated CustomerStatus customerStatus) {
        int status;
        CustomerStatusDO customerStatusDO = dao.findByAccountId(customerStatus.getAccountId(), customerStatus.getOrgId());
        if (customerStatusDO == null) {
            status = dao.insertSelective(modelToDO(customerStatus));
            return Result.ok(status);
        }

        status = dao.updateByAccountId(modelToDO(customerStatus), customerStatus.getAccountId(), customerStatus.getOrgId());
        return Result.ok(status);
    }

    private Result<CustomerStatus> initStatus(Long accountId, Long orgId) {
        CustomerStatus status = new CustomerStatus();
        status.setAccountId(accountId);
        status.setOrgId(orgId);

        CustomerStatusDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return Result.ok(status);
    }

    private CustomerStatus DOToModel(CustomerStatusDO customerStatusDO) {
        if (customerStatusDO == null) {
            return null;
        }

        CustomerStatus customerStatus = new CustomerStatus();
        BeanUtils.copyProperties(customerStatusDO, customerStatus);

        return customerStatus;
    }

    private CustomerStatusDO modelToDO(CustomerStatus customerStatus) {
        if (customerStatus == null) {
            return null;
        }

        CustomerStatusDO customerStatusDO = new CustomerStatusDO();
        BeanUtils.copyProperties(customerStatus, customerStatusDO);

        return customerStatusDO;
    }

}
