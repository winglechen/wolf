package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.api.setting.entity.StaffSetting;
import study.daydayup.wolf.business.uc.api.setting.service.StaffSettingService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.StaffSettingDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.StaffSettingDO;
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
public class StaffSettingServiceImpl implements StaffSettingService {
    @Resource
    private StaffSettingDAO dao;

    @Override
    public Result<StaffSetting> find(@NonNull Long accountId, @NonNull Long orgId) {
        StaffSettingDO customerSettingDO = dao.findByAccountId(accountId, orgId);
        if (customerSettingDO == null) {
            return initSetting(accountId, orgId);
        }
        StaffSetting setting = DOToModel(customerSettingDO);
        return Result.ok(setting);
    }

    @Override
    public Result<Integer> save(@Validated StaffSetting customerSetting) {
        int status;
        StaffSettingDO customerSettingDO = dao.findByAccountId(customerSetting.getAccountId(), customerSetting.getOrgId());
        if (customerSettingDO == null) {
            status = dao.insertSelective(modelToDO(customerSetting));
            return Result.ok(status);
        }

        status = dao.updateByAccountId(modelToDO(customerSetting), customerSetting.getAccountId(), customerSetting.getOrgId());
        return Result.ok(status);
    }

    private Result<StaffSetting> initSetting(Long accountId, Long orgId) {
        StaffSetting status = new StaffSetting();
        status.setAccountId(accountId);
        status.setOrgId(orgId);

        StaffSettingDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return Result.ok(status);
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
