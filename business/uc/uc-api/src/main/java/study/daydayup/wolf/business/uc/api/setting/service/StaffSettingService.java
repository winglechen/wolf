package study.daydayup.wolf.business.uc.api.setting.service;

import study.daydayup.wolf.business.uc.api.setting.entity.StaffSetting;
import study.daydayup.wolf.framework.layer.domain.Service;

/**
 * study.daydayup.wolf.business.uc.api.account.service
 *
 * @author Wingle
 * @since 2019/12/31 7:46 下午
 **/
public interface StaffSettingService extends Service {
    StaffSetting find(Long accountId, Long orgId);
    void save(StaffSetting staffSetting);
}
