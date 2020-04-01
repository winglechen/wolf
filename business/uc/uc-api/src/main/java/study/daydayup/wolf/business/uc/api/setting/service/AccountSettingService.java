package study.daydayup.wolf.business.uc.api.setting.service;

import study.daydayup.wolf.business.uc.api.setting.dto.SettingDTO;
import study.daydayup.wolf.business.uc.api.setting.entity.AccountSetting;
import study.daydayup.wolf.framework.layer.domain.Service;
import study.daydayup.wolf.framework.rpc.Result;

import java.util.List;

/**
 * study.daydayup.wolf.business.uc.api.account.service
 *
 * @author Wingle
 * @since 2019/12/31 7:46 下午
 **/
public interface AccountSettingService extends Service {
    Result<AccountSetting> find(Long accountId);
    Result<Integer> replace(AccountSetting accountSetting);

    Result<Integer> set(SettingDTO settingDTO);
    Result<List<AccountSetting>> findByNamespaces(SettingDTO settingDTO);
    Result<List<AccountSetting>> findAll(Long accountId);
}
