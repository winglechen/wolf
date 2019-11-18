package study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator;

import com.alibaba.fastjson.JSON;
import study.daydayup.wolf.demo.account.api.dto.request.authorization.WechatAuthorizationRequest;
import study.daydayup.wolf.demo.account.biz.authorization.repository.AuthorizationRepository;
import study.daydayup.wolf.demo.account.biz.authorization.vo.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountWechatVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatSessionKeyVO;

@Getter
@Setter
@Slf4j
public class WechatMiniAuthenticator implements Authenticator<WechatAuthorizationRequest> {

    private WechatSessionKeyVO wechatSessionKeyVO;

    private AccountWechatVO accountWechat;

    private AuthorizationRepository authorizationRepository;

    public WechatMiniAuthenticator(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public AccountVO authenticate(WechatAuthorizationRequest request) {
        wechatSessionKeyVO = authorizationRepository.getSessionKey(request.getCode());
        AccountVO accountVO = getAccount();
        authorizationRepository.getWithCreateWechatSessionKey(accountWechat, wechatSessionKeyVO);
        return accountVO;
    }

    private AccountVO getAccount() {
        accountWechat = authorizationRepository.getAccountWechatByUnionId(wechatSessionKeyVO.getUnionId());
        if (accountWechat != null) {
            if (StringUtils.isEmpty(accountWechat.getMpOpenId())) {
                log.info("wechat mini authenticate accountWechat:{}, wechatSessionKeyVO:{}", JSON.toJSONString(accountWechat), JSON.toJSONString(wechatSessionKeyVO));
                authorizationRepository.updateMpOpenIdByUidAndUnionId(accountWechat.getUid(), accountWechat.getUnionId(), wechatSessionKeyVO.getOpenId());
            }

            return new AccountVO(accountWechat.getUid());
        }

        AccountVO accountVO = authorizationRepository.createAccount(wechatSessionKeyVO);
        accountWechat = authorizationRepository.createAccountWechat(accountVO.getUid(), "", wechatSessionKeyVO.getOpenId(), wechatSessionKeyVO.getUnionId());
        return accountVO;
    }
}