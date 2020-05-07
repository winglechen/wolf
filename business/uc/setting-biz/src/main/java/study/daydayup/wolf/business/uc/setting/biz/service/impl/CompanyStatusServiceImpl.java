package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.setting.api.entity.CompanyStatus;
import study.daydayup.wolf.business.uc.setting.api.service.CompanyStatusService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.CompanyStatusDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.CompanyStatusDO;
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
public class CompanyStatusServiceImpl implements CompanyStatusService {
    @Resource
    private CompanyStatusDAO dao;
    @Override
    public Result<CompanyStatus> find(@NonNull Long companyId) {
        CompanyStatusDO companyStatusDO = dao.findByOrgId(companyId);
        if (companyStatusDO == null) {
            return initStatus(companyId);
        }
        CompanyStatus status = DOToModel(companyStatusDO);
        return Result.ok(status);
    }

    @Override
    public Result<Integer> save(@Validated CompanyStatus companyStatus) {
        int status;
        CompanyStatusDO companyStatusDO = dao.findByOrgId(companyStatus.getOrgId());
        if (companyStatusDO == null) {
            status = dao.insertSelective(modelToDO(companyStatus));
            return Result.ok(status);
        }
        status = dao.updateByOrgId(modelToDO(companyStatus), companyStatus.getOrgId());
        return Result.ok(status);
    }

    private Result<CompanyStatus> initStatus(Long companyId) {
        CompanyStatus status = new CompanyStatus();
        status.setOrgId(companyId);

        CompanyStatusDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return Result.ok(status);
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
