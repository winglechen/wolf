package study.daydayup.wolf.demo.account.biz.authorization.entity.authenticator;

import study.daydayup.wolf.demo.account.api.dto.request.auth.WechatRequest;
import study.daydayup.wolf.demo.account.biz.authorization.repository.AuthorizationRepository;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.AccountWechatVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatMpOAuthResponseVO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.WechatMpUserInfoResponseVO;

@Getter
@Setter
public class WechatAuthenticator implements Authenticator<WechatRequest> {

    private WechatMpOAuthResponseVO wechatMpOAuthResponse;

    private WechatMpUserInfoResponseVO wechatMpUserInfoResponse;

    private AccountWechatVO accountWechat;
    
    private AuthorizationRepository authorizationRepository;

    public WechatAuthenticator(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public AccountVO authenticate(WechatRequest request) {
        wechatMpOAuthResponse = authorizationRepository.authorize(request.getAuthorizationType(), request.getCode());
        AccountVO accountVO = getAccount();

        if (StringUtils.isEmpty(accountWechat.getOpenId())) {
            authorizationRepository.updateOpenIdByUidAndUnionId(accountWechat.getUid(), accountWechat.getUnionId(), wechatMpOAuthResponse.getOpenId());
        }

        authorizationRepository.createWechatAccessToken(accountWechat, wechatMpOAuthResponse);
        return accountVO;
    }

    private AccountVO getAccount() {
        accountWechat = authorizationRepository.getAccountWechatByUnionId(wechatMpOAuthResponse.getUnionId());
        if (accountWechat != null) {
            return new AccountVO(accountWechat.getUid());
        }

        wechatMpUserInfoResponse = authorizationRepository.getWechatUserInfo(wechatMpOAuthResponse.getAccessToken(), wechatMpOAuthResponse.getOpenId());
        AccountVO accountVO = authorizationRepository.createAccount(wechatMpOAuthResponse, wechatMpUserInfoResponse);
        accountWechat = authorizationRepository.createAccountWechat(accountVO.getUid(), wechatMpOAuthResponse.getOpenId(), "", wechatMpOAuthResponse.getUnionId());
        authorizationRepository.saveUserDetail(accountWechat, wechatMpUserInfoResponse);
        return accountVO;
    }

}
