package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.api.setting.entity.CompanyStatus;
import study.daydayup.wolf.business.uc.api.setting.service.CompanyStatusService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.CompanyStatusDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CompanyStatusDO;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:38 下午
 **/
@RpcService(protocol = "dubbo")
public class CompanyStatusServiceImpl implements CompanyStatusService {
    @Resource
    private CompanyStatusDAO dao;
    @Override
    public CompanyStatus find(Long companyId) {
        if (companyId == null) {
            return null;
        }
        CompanyStatusDO companyStatusDO = dao.findByOrgId(companyId);
        if (companyStatusDO == null) {
            return initStatus(companyId);
        }
        return DOToModel(companyStatusDO);
    }

    @Override
    public void save(@Validated CompanyStatus companyStatus) {
        if (companyStatus == null) {
            return;
        }

        CompanyStatusDO companyStatusDO = dao.findByOrgId(companyStatus.getOrgId());
        if (companyStatusDO == null) {
            dao.insertSelective(modelToDO(companyStatus));
            return;
        }
        dao.updateByOrgId(modelToDO(companyStatus), companyStatus.getOrgId());
    }

    private CompanyStatus initStatus(Long companyId) {
        CompanyStatus status = new CompanyStatus();
        status.setOrgId(companyId);

        CompanyStatusDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return status;
    }

    private CompanyStatus DOToModel(CompanyStatusDO companyStatusDO) {
        if (companyStatusDO == null) {
            return null;
        }

        CompanyStatus companyStatus = new CompanyStatus();
        BeanUtils.copyProperties(companyStatusDO, companyStatus);

        return companyStatus;
    }

    private CompanyStatusDO modelToDO(CompanyStatus companyStatus) {
        if (companyStatus == null) {
            return null;
        }

        CompanyStatusDO companyStatusDO = new CompanyStatusDO();
        BeanUtils.copyProperties(companyStatus, companyStatusDO);

        return companyStatusDO;
    }
}
