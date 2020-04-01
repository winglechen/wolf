package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.api.setting.entity.AccountSetting;
import study.daydayup.wolf.business.uc.api.setting.service.AccountSettingService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.AccountSettingDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.AccountSettingDO;
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
public class AccountSettingServiceImpl implements AccountSettingService {
    @Resource
    private AccountSettingDAO dao;
    @Override
    public Result<AccountSetting> find(Long accountId) {
        if (accountId == null) {
            return Result.fail(10000, "invalid args");
        }
        AccountSettingDO accountSettingDO = dao.findByAccountId(accountId);
        if (accountSettingDO == null) {
            return initSetting(accountId);
        }
        AccountSetting accountSetting = DOToModel(accountSettingDO);
        if (accountSetting == null) {
            return Result.fail(10000, "invalid args");
        }

        return Result.ok(accountSetting);
    }

    @Override
    public Result<Integer> save(@Validated AccountSetting accountSetting) {
        if (accountSetting == null) {
            return Result.fail(10000, "invalid args", 0);
        }

        AccountSettingDO accountSettingDO = dao.findByAccountId(accountSetting.getAccountId());
        if (accountSettingDO == null) {
            dao.insertSelective(modelToDO(accountSetting));
            return Result.fail(10000, "invalid args", 0);
        }
        int status = dao.updateByAccountId(modelToDO(accountSetting), accountSetting.getAccountId());
        return Result.ok(status);
    }

    private Result<AccountSetting> initSetting(Long accountId) {
        AccountSetting status = new AccountSetting();
        status.setAccountId(accountId);

        AccountSettingDO statusDO = modelToDO(status);
        if (statusDO == null) {
            return Result.fail(10000, "invalid args");
        }

        dao.insertSelective(statusDO);
        return Result.ok(status);
    }

    private AccountSetting DOToModel(AccountSettingDO accountSettingDO) {
        if (accountSettingDO == null) {
            return null;
        }

        AccountSetting accountSetting = new AccountSetting();
        BeanUtils.copyProperties(accountSettingDO, accountSetting);

        return accountSetting;
    }

    private AccountSettingDO modelToDO(AccountSetting accountSetting) {
        if (accountSetting == null) {
            return null;
        }

        AccountSettingDO accountSettingDO = new AccountSettingDO();
        BeanUtils.copyProperties(accountSetting, accountSettingDO);

        return accountSettingDO;
    }
}
