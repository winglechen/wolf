package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.setting.api.dto.SettingDTO;
import study.daydayup.wolf.business.uc.setting.api.entity.AccountSetting;
import study.daydayup.wolf.business.uc.setting.api.entity.KvData;
import study.daydayup.wolf.business.uc.setting.api.service.AccountSettingService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.AccountSettingDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.AccountSettingDO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;
import java.util.List;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:38 下午
 **/
@RpcService
public class AccountSettingServiceImpl implements AccountSettingService {
    @Resource
    private AccountSettingDAO dao;
    @Override
    public Result<AccountSetting> find(Long accountId) {
        if (accountId == null) {
            return Result.fail(10000, "invalid args");
        }
        AccountSettingDO accountSettingDO = dao.findByNamespace(KvData.DEFAULT_NAMESPACE, accountId);
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
    public Result<Integer> replace(@Validated AccountSetting accountSetting) {
        if (accountSetting == null) {
            return Result.fail(10000, "invalid args", 0);
        }

        AccountSettingDO accountSettingDO = dao.findByNamespace(KvData.DEFAULT_NAMESPACE, accountSetting.getAccountId());
        if (accountSettingDO == null) {
            dao.insertSelective(modelToDO(accountSetting));
            return Result.fail(10000, "invalid args", 0);
        }
        int status = dao.updateByAccountId(modelToDO(accountSetting), accountSetting.getAccountId());
        return Result.ok(status);
    }

    @Override
    public Result<Integer> set(SettingDTO settingDTO) {
        return null;
}

    @Override
    public Result<List<AccountSetting>> findByNamespaces(SettingDTO settingDTO) {
        return null;
    }

    @Override
    public Result<List<AccountSetting>> findAll(Long accountId) {
        return null;
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
