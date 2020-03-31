package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.api.setting.entity.CustomerSetting;
import study.daydayup.wolf.business.uc.api.setting.service.CustomerSettingService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.CustomerSettingDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CustomerSettingDO;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:39 下午
 **/
@RpcService(protocol = "dubbo")
public class CustomerSettingServiceImpl implements CustomerSettingService {
    @Resource
    private CustomerSettingDAO dao;

    @Override
    public CustomerSetting find(Long accountId, Long orgId) {
        if (null == accountId || null == orgId) {
            return null;
        }

        CustomerSettingDO customerSettingDO = dao.findByAccountId(accountId, orgId);
        if (customerSettingDO == null) {
            return initSetting(accountId, orgId);
        }
        return DOToModel(customerSettingDO);
    }

    @Override
    public void save(@Validated CustomerSetting customerSetting) {
        if (customerSetting == null) {
            return;
        }

        CustomerSettingDO customerSettingDO = dao.findByAccountId(customerSetting.getAccountId(), customerSetting.getOrgId());
        if (customerSettingDO == null) {
            dao.insertSelective(modelToDO(customerSetting));
            return;
        }

        dao.updateByAccountId(modelToDO(customerSetting), customerSetting.getAccountId(), customerSetting.getOrgId());
    }

    private CustomerSetting initSetting(Long accountId, Long orgId) {
        CustomerSetting status = new CustomerSetting();
        status.setAccountId(accountId);
        status.setOrgId(orgId);

        CustomerSettingDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return status;
    }

    private CustomerSetting DOToModel(CustomerSettingDO customerSettingDO) {
        if (customerSettingDO == null) {
            return null;
        }

        CustomerSetting customerSetting = new CustomerSetting();
        BeanUtils.copyProperties(customerSettingDO, customerSetting);

        return customerSetting;
    }

    private CustomerSettingDO modelToDO(CustomerSetting customerSetting) {
        if (customerSetting == null) {
            return null;
        }

        CustomerSettingDO customerSettingDO = new CustomerSettingDO();
        BeanUtils.copyProperties(customerSetting, customerSettingDO);

        return customerSettingDO;
    }

}
