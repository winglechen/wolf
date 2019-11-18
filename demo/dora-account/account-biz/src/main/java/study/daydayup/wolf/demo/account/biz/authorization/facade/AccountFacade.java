package study.daydayup.wolf.demo.account.biz.authorization.facade;

import study.daydayup.wolf.demo.account.api.dto.request.account.AccountRequest;
import study.daydayup.wolf.demo.account.api.enums.AccountSourceEnum;
import study.daydayup.wolf.demo.account.api.enums.AccountTypeEnum;
import study.daydayup.wolf.demo.account.api.enums.GenderEnum;
import study.daydayup.wolf.demo.account.api.model.Account;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatMpOAuthResponseVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatMpUserInfoResponseVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatSessionKeyVO;
import study.daydayup.wolf.demo.account.biz.service.AccountInnerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountFacade {

    @Resource
    private AccountInnerService accountInnerService;

    public AccountVO createAccount(WechatMpOAuthResponseVO wechatMpOAuthResponseVO, WechatMpUserInfoResponseVO wechatMpUserInfoResponseVO) {
        Account account = null;
        try {
            account = accountInnerService.getByAccount(wechatMpOAuthResponseVO.getUnionId());
        } catch (Exception e) {}
        if (account != null) {
            return new AccountVO(account.getId());
        }

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccount(wechatMpOAuthResponseVO.getUnionId());
        accountRequest.setPassword("");
        accountRequest.setType(AccountTypeEnum.WECHAT_UNION_ID.getType());
        accountRequest.setSource(AccountSourceEnum.WECHAT.getSource());
        accountRequest.setNickname(wechatMpUserInfoResponseVO.getNickname());
        accountRequest.setGender(wechatMpUserInfoResponseVO.getGender());
        accountRequest.setAvatar(wechatMpUserInfoResponseVO.getAvatar());
        account = accountInnerService.create(accountRequest);
        return new AccountVO(account.getId());
    }

    public AccountVO getWithCreateAccount(String mobile) {
        Account account = accountInnerService.getWithCreateAccount(mobile);
        return new AccountVO(account.getId());
    }

    public AccountVO createAccount(WechatSessionKeyVO wechatSessionKeyVO) {
        Account account = null;
        try {
            account = accountInnerService.getByAccount(wechatSessionKeyVO.getUnionId());
        } catch (Exception e) {}
        if (account != null) {
            return new AccountVO(account.getId());
        }

        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAccount(wechatSessionKeyVO.getUnionId());
        accountRequest.setPassword("");
        accountRequest.setType(AccountTypeEnum.WECHAT_UNION_ID.getType());
        accountRequest.setSource(AccountSourceEnum.WECHAT.getSource());
        accountRequest.setNickname(wechatSessionKeyVO.getUnionId());
        accountRequest.setGender(GenderEnum.UNKNOWN.getCode());
        accountRequest.setAvatar("");
        account = accountInnerService.create(accountRequest);
        return new AccountVO(account.getId());
    }
}
