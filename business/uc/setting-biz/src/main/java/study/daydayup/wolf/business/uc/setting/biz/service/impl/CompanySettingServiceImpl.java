package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.api.setting.entity.CompanySetting;
import study.daydayup.wolf.business.uc.api.setting.service.CompanySettingService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.CompanySettingDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CompanySettingDO;
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
    public CompanySetting find(Long companyId) {
        if (companyId == null) {
            return null;
        }
        CompanySettingDO companySettingDO = dao.findByOrgId(companyId);
        if (companySettingDO == null) {
            return initSetting(companyId);
        }
        return DOToModel(companySettingDO);
    }

    @Override
    public void save(@Validated CompanySetting companySetting) {
        if (companySetting == null) {
            return;
        }

        CompanySettingDO companySettingDO = dao.findByOrgId(companySetting.getOrgId());
        if (companySettingDO == null) {
            dao.insertSelective(modelToDO(companySetting));
            return;
        }
        dao.updateByOrgId(modelToDO(companySetting), companySetting.getOrgId());
    }

    private CompanySetting initSetting(Long companyId) {
        CompanySetting status = new CompanySetting();
        status.setOrgId(companyId);

        CompanySettingDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return status;
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
