package study.daydayup.wolf.business.uc.api.setting.service;

import study.daydayup.wolf.business.uc.api.setting.dto.SettingDTO;
import study.daydayup.wolf.business.uc.api.setting.entity.StaffSetting;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.api.account.service
 *
 * @author Wingle
 * @since 2019/12/31 7:46 下午
 **/
public interface StaffSettingService extends Service {
    Result<StaffSetting> find(Long accountId, Long orgId);
    Result<Integer> save(StaffSetting staffSetting);

    Result<Integer> set(SettingDTO settingDTO);
    Result<List<StaffSetting>> findByNamespaces(SettingDTO settingDTO);
    Result<List<StaffSetting>> findAll(Long accountId);
}
