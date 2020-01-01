package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.api.setting.entity.CustomerStatus;
import study.daydayup.wolf.business.uc.api.setting.service.CustomerStatusService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.CustomerStatusDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CustomerStatusDO;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:39 下午
 **/
@RpcService(protocol = "dubbo")
public class CustomerStatusServiceImpl implements CustomerStatusService {
    @Resource
    private CustomerStatusDAO dao;

    @Override
    public CustomerStatus find(Long accountId, Long orgId) {
        if (null == accountId || null == orgId) {
            return null;
        }

        CustomerStatusDO customerStatusDO = dao.findByAccountId(accountId, orgId);
        if (customerStatusDO == null) {
            return initStatus(accountId, orgId);
        }
        return DOToModel(customerStatusDO);
    }

    @Override
    public void save(@Validated CustomerStatus customerStatus) {
        if (customerStatus == null) {
            return;
        }

        CustomerStatusDO customerStatusDO = dao.findByAccountId(customerStatus.getAccountId(), customerStatus.getOrgId());
        if (customerStatusDO == null) {
            dao.insertSelective(modelToDO(customerStatus));
            return;
        }

        dao.updateByAccountId(modelToDO(customerStatus), customerStatus.getAccountId(), customerStatus.getOrgId());
    }

    private CustomerStatus initStatus(Long accountId, Long orgId) {
        CustomerStatus status = new CustomerStatus();
        status.setAccountId(accountId);
        status.setOrgId(orgId);

        CustomerStatusDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return status;
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
