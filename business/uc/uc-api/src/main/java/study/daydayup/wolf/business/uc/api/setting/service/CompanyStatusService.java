package study.daydayup.wolf.business.uc.api.setting.service;

import study.daydayup.wolf.business.uc.api.setting.entity.CompanyStatus;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

/**
 * study.daydayup.wolf.business.uc.api.company.service
 *
 * @author Wingle
 * @since 2019/12/31 7:46 下午
 **/
public interface CompanyStatusService extends Service {
    Result<CompanyStatus> find(Long companyId);
    Result<Integer> save(CompanyStatus companyStatus);
}
