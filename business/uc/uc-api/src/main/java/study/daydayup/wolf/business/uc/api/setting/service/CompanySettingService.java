package study.daydayup.wolf.business.uc.api.setting.service;

import study.daydayup.wolf.business.uc.api.setting.dto.SettingDTO;
import study.daydayup.wolf.business.uc.api.setting.entity.CompanySetting;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

import java.util.Collection;
import java.util.List;

/**
 * study.daydayup.wolf.business.uc.api.company.service
 *
 * @author Wingle
 * @since 2019/12/31 7:46 下午
 **/
public interface CompanySettingService extends Service {
    Result<CompanySetting> find(Long companyId);
    Result<Integer> save(CompanySetting companySetting);

    Result<CompanySetting> findByNamespace(SettingDTO settingDTO);
    Result<List<CompanySetting>> findAll(Long companyId);
    Result<List<CompanySetting>> findByOrgIds(Collection<Long> companyIds);
    Result<List<CompanySetting>> findByOrgIds(String namespace, Collection<Long> companyIds);

}
