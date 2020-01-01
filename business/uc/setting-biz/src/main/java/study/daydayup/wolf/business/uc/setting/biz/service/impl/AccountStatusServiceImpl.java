package study.daydayup.wolf.business.uc.setting.biz.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import study.daydayup.wolf.business.uc.api.setting.entity.AccountStatus;
import study.daydayup.wolf.business.uc.api.setting.service.AccountStatusService;
import study.daydayup.wolf.business.uc.setting.biz.dal.dao.AccountStatusDAO;
import study.daydayup.wolf.business.uc.setting.biz.dal.dataobject.AccountStatusDO;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.business.uc.setting.biz.service.impl
 *
 * @author Wingle
 * @since 2020/1/1 12:38 下午
 **/
@RpcService(protocol = "dubbo")
public class AccountStatusServiceImpl implements AccountStatusService {
    @Resource
    private AccountStatusDAO dao;
    @Override
    public AccountStatus find(Long accountId) {
        if (accountId == null) {
            return null;
        }
        AccountStatusDO accountStatusDO = dao.findByAccountId(accountId);
        if (accountStatusDO == null) {
            return initStatus(accountId);
        }
        return DOToModel(accountStatusDO);
    }

    @Override
    public void save(@Validated AccountStatus accountStatus) {
        if (accountStatus == null) {
            return;
        }

        AccountStatusDO accountStatusDO = dao.findByAccountId(accountStatus.getAccountId());
        if (accountStatusDO == null) {
            dao.insertSelective(modelToDO(accountStatus));
            return;
        }
        dao.updateByAccountId(modelToDO(accountStatus), accountStatus.getAccountId());
    }

    private AccountStatus initStatus(Long accountId) {
        AccountStatus status = new AccountStatus();
        status.setAccountId(accountId);

        AccountStatusDO statusDO = modelToDO(status);
        dao.insertSelective(statusDO);

        return status;
    }

    private AccountStatus DOToModel(AccountStatusDO accountStatusDO) {
        if (accountStatusDO == null) {
            return null;
        }

        AccountStatus accountStatus = new AccountStatus();
        BeanUtils.copyProperties(accountStatusDO, accountStatus);

        return accountStatus;
    }

    private AccountStatusDO modelToDO(AccountStatus accountStatus) {
        if (accountStatus == null) {
            return null;
        }

        AccountStatusDO accountStatusDO = new AccountStatusDO();
        BeanUtils.copyProperties(accountStatus, accountStatusDO);

        return accountStatusDO;
    }
}
