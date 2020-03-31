package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.api.setting.entity.StaffSetting;
import study.daydayup.wolf.business.uc.api.setting.service.StaffSettingService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.StaffSettingDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.StaffSettingDO;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:39 下午
 **/
@RpcService(protocol = "dubbo")
public class StaffSettingServiceImpl implements StaffSettingService {
    @Resource
    private StaffSettingDAO dao;

    @Override
    public StaffSetting find(Long accountId, Long orgId) {
        if (null == accountId || null == orgId) {
            return null;
        }

        StaffSettingDO customerSettingDO = dao.findByAccountId(accountId, orgId);
        if (customerSettingDO == null) {
            return initSetting(accountId, orgId);
        }
        return DOToModel(customerSettingDO);
    }

    @Override
    public void save(@Validated StaffSetting customerSetting) {
        if (customerSetting == null) {
            return;
        }

        StaffSettingDO customerSettingDO = dao.findByAccountId(customerSetting.getAccountId(), customerSetting.getOrgId());
        if (customerSettingDO == null) {
            dao.insertSelective(modelToDO(customerSetting));
            return;
        }

        dao.updateByAccountId(modelToDO(customerSetting), customerSetting.getAccountId(), customerSetting.getOrgId());
    }

    private StaffSetting initSetting(Long accountId, Long orgId) {
        StaffSetting status = new StaffSetting();
        status.setAccountId(accountId);
        status.setOrgId(orgId);

        StaffSettingDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return status;
    }

    private StaffSetting DOToModel(StaffSettingDO customerSettingDO) {
        if (customerSettingDO == null) {
            return null;
        }

        StaffSetting customerSetting = new StaffSetting();
        BeanUtils.copyProperties(customerSettingDO, customerSetting);

        return customerSetting;
    }

    private StaffSettingDO modelToDO(StaffSetting customerSetting) {
        if (customerSetting == null) {
            return null;
        }

        StaffSettingDO customerSettingDO = new StaffSettingDO();
        BeanUtils.copyProperties(customerSetting, customerSettingDO);

        return customerSettingDO;
    }

}
