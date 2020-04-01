package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.api.setting.entity.CompanySetting;
import study.daydayup.wolf.business.uc.api.setting.entity.KvData;
import study.daydayup.wolf.business.uc.api.setting.service.CompanySettingService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.CompanySettingDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CompanySettingDO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:38 下午
 **/
@RpcService(protocol = "dubbo")
public class CompanySettingServiceImpl implements CompanySettingService {
    @Resource
    private CompanySettingDAO dao;
    @Override
    public Result<CompanySetting> find(Long companyId) {
        if (companyId == null) {
            return Result.fail(10000, "invalid args");
        }
        CompanySettingDO companySettingDO = dao.findByNamespace(KvData.DEFAULT_NAMESPACE, companyId);
        if (companySettingDO == null) {
            return initSetting(companyId);
        }
        CompanySetting setting = DOToModel(companySettingDO);
        return Result.ok(setting);
    }

    @Override
    public Result<Integer> save(@Validated CompanySetting companySetting) {
        int status;
        CompanySettingDO companySettingDO = dao.findByNamespace(KvData.DEFAULT_NAMESPACE, companySetting.getOrgId());
        if (companySettingDO == null) {
            status = dao.insertSelective(modelToDO(companySetting));
            return Result.ok(status);
        }
        status = dao.updateByOrgId(modelToDO(companySetting), companySetting.getOrgId());
        return Result.ok(status);
    }

    private Result<CompanySetting> initSetting(Long companyId) {
        CompanySetting status = new CompanySetting();
        status.setOrgId(companyId);

        CompanySettingDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return Result.ok(status);
    }

    private CompanySetting DOToModel(CompanySettingDO companySettingDO) {
        if (companySettingDO == null) {
            return null;
        }

        CompanySetting companySetting = new CompanySetting();
        BeanUtils.copyProperties(companySettingDO, companySetting);

        return companySetting;
    }

    private CompanySettingDO modelToDO(CompanySetting companySetting) {
        if (companySetting == null) {
            return null;
        }

        CompanySettingDO companySettingDO = new CompanySettingDO();
        BeanUtils.copyProperties(companySetting, companySettingDO);

        return companySettingDO;
    }
}
