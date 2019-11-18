package study.daydayup.wolf.demo.account.biz.service.impl;

import study.daydayup.wolf.demo.account.api.dto.AccountWechatSessionKey;
import study.daydayup.wolf.demo.account.api.dto.request.account.AccountRequest;
import study.daydayup.wolf.demo.account.api.entity.Account;
import study.daydayup.wolf.demo.account.api.service.AccountService;
import study.daydayup.wolf.demo.account.biz.dal.dao.AccountWechatDAO;
import study.daydayup.wolf.demo.account.biz.dal.dao.WechatSessionKeyDAO;
import study.daydayup.wolf.demo.account.biz.dal.dataobject.AccountWechatDO;
import study.daydayup.wolf.demo.account.biz.dal.dataobject.WechatSessionKeyDO;
import study.daydayup.wolf.demo.account.biz.service.AccountInnerService;
import study.daydayup.wolf.framework.rpc.RpcService;

import javax.annotation.Resource;

@RpcService
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountInnerService accountInnerService;

    @Resource
    private WechatSessionKeyDAO wechatSessionKeyDAO;

    @Resource
    private AccountWechatDAO accountWechatDAO;

    @Override
    public Account create(AccountRequest accountRequest) {
        return accountInnerService.create(accountRequest);
    }

    @Override
    public Account getByAccount(String account) {
        return accountInnerService.getByAccount(account);
    }

    @Override
    public Account getById(Long id) {
        return accountInnerService.getById(id);
    }

    @Override
    public AccountWechatSessionKey getAccountWechatSessionKey(Long id) {
        WechatSessionKeyDO wechatSessionKeyDO = wechatSessionKeyDAO.getOneByUid(id);
        if (wechatSessionKeyDO == null) {
            return null;
        }
        AccountWechatDO accountWechatDO = accountWechatDAO.selectOneByUnionId(wechatSessionKeyDO.getUnionId());
        AccountWechatSessionKey accountWechatSessionKey = new AccountWechatSessionKey();
        accountWechatSessionKey.setUid(wechatSessionKeyDO.getUid());
        accountWechatSessionKey.setOpenId(accountWechatDO != null ? accountWechatDO.getOpenId() : "");
        accountWechatSessionKey.setMiniOpenId(wechatSessionKeyDO.getOpenId());
        accountWechatSessionKey.setUnionId(wechatSessionKeyDO.getUnionId());
        accountWechatSessionKey.setSessionKey(wechatSessionKeyDO.getSessionKey());
        return accountWechatSessionKey;
    }


}
